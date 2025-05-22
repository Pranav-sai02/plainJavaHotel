package com.neoteric.plainJavaHotel.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.List;


@Service
public class HotelReviewService {

    @Autowired
    private LocationService locationService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ImageRepository imageRepository;

    public CompletableFuture<Void> saveFullHotelDetailsAsync(HotelFullRequestDTO dto) {
        // Step 1: Save Location asynchronously
        Location location = new Location();
        location.setName(dto.getLocationName());
        location.setLatitude(dto.getLocationLatitude());
        location.setLongitude(dto.getLocationLongitude());

        CompletableFuture<Location> savedLocationFuture = locationService.saveLocationAsync(location);


        return savedLocationFuture.thenCompose(savedLocation -> {
            Hotel hotel = new Hotel();
            hotel.setHotelName(dto.getHotelName());
            hotel.setLatitude(dto.getHotelLatitude());
            hotel.setLongitude(dto.getHotelLongitude());
            hotel.setLocation(savedLocation);

            List<Review> reviewList = new ArrayList<>();
            if (dto.getReviews() != null) {
                for (ReviewDTO rDto : dto.getReviews()) {
                    Review review = new Review();
                    review.setRating(rDto.getRating());
                    review.setText(rDto.getText());
                    review.setLocationId(savedLocation.getId());
                    review.setName(hotel.getHotelName());
                    review.setHotel(hotel);
                    reviewList.add(review);
                }
            }

            List<Image> imageList = new ArrayList<>();
            if (dto.getImages() != null) {
                for (ImageDTO iDto : dto.getImages()) {
                    Image image = new Image();
                    image.setUrl(iDto.getUrl());
                    image.setDescription(iDto.getDescription());
                    image.setLocationId(savedLocation.getId());
                    image.setName(hotel.getHotelName());
                    image.setHotel(hotel);
                    imageList.add(image);
                }
            }

            hotel.setReviews(reviewList);
            hotel.setImages(imageList);

            return hotelService.saveHotelAsync(hotel).thenAccept(savedHotel -> {

                System.out.println("Hotel with related data saved successfully: " + savedHotel.getHotelName());
            });
        });
    }



    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }
    public CompletableFuture<Hotel> saveHotelOnlyAsync(Hotel hotel) {
        return CompletableFuture.supplyAsync(() -> hotelService.saveHotel(hotel));
    }

    public CompletableFuture<Location> saveLocationOnlyAsync(Location location) {
        return locationService.saveLocationAsync(location);
    }


}
