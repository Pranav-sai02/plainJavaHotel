package com.neoteric.plainJavaHotel.hotelreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private HotelRepository hotelRepository;

    public List<Review> getHotelReviewListByLocationAndName(int locationId, String name) {
        return reviewRepository.findByLocationIdAndName(locationId, name);
    }

    public List<Image> getHotelImageListByLocationAndName(int locationId, String name) {
        return imageRepository.findByLocationIdAndName(locationId, name);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    // ✅ MAIN METHOD: Save Location, Hotel, Reviews, and Images in one go
    public void saveFullHotelDetails(HotelFullRequestDTO dto) {
        // Step 1: Save Location
        Location location = new Location();
        location.setName(dto.getLocationName());
        location.setLatitude(dto.getLocationLatitude());
        location.setLongitude(dto.getLocationLongitude());
        Location savedLocation = locationService.saveLocation(location);

        // Step 2: Save Hotel with Location
        Hotel hotel = new Hotel();
        hotel.setHotelName(dto.getHotelName());
        hotel.setLatitude(dto.getHotelLatitude());
        hotel.setLongitude(dto.getHotelLongitude());
        hotel.setLocation(savedLocation);

        // Prepare reviews and images (link hotel + set locationId and name)
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

        // Step 3: Set child lists and save hotel
        hotel.setReviews(reviewList);
        hotel.setImages(imageList);

        hotelService.saveHotel(hotel); // this will cascade and save reviews/images too
    }

    // Optional single saves for controller
    public Hotel saveHotelOnly(Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }

    public Location saveLocationOnly(Location location) {
        return locationService.saveLocation(location);
    }

}
