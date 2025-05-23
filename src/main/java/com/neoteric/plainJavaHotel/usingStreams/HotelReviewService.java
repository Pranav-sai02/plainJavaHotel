package com.neoteric.plainJavaHotel.usingStreams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


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

            List<Review> reviewList = dto.getReviews() == null ? new ArrayList<>() :
                    dto.getReviews().stream()
                            .map(rDto -> {
                        Review review = new Review();
                        review.setRating(rDto.getRating());
                        review.setText(rDto.getText());
                        review.setLocationId(savedLocation.getId());
                        review.setName(hotel.getHotelName());
                        review.setHotel(hotel);
                        return review;
                    }).toList();


            List<Image> imageList = dto.getImages() == null ? new ArrayList<>() :
                    dto.getImages().stream()
                            .map(iDto -> {
                        Image image = new Image();
                        image.setUrl(iDto.getUrl());
                        image.setDescription(iDto.getDescription());
                        image.setLocationId(savedLocation.getId());
                        image.setName(hotel.getHotelName());
                        image.setHotel(hotel);
                        return image;
                    }).toList();


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
