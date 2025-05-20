package com.neoteric.plainJavaHotel.hotelreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelReviewService hotelReviewService;

    @Autowired
    private HotelService hotelService;


    @PostMapping("/addFullHotel")
    public String addFullHotel(@RequestBody HotelFullRequestDTO dto) {
        hotelReviewService.saveFullHotelDetails(dto);
        return "Hotel, location, reviews, and images saved successfully!";
    }


    @PostMapping("/addHotel")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelReviewService.saveHotelOnly(hotel);
    }

    @PostMapping("/addLocation")
    public Location addLocation(@RequestBody Location location) {
        return hotelReviewService.saveLocationOnly(location);
    }

    @PostMapping("/addReview")
    public Review addReview(@RequestBody Review review) {
        return hotelReviewService.saveReview(review);
    }

    @PostMapping("/addImage")
    public Image addImage(@RequestBody Image image) {
        return hotelReviewService.saveImage(image);
    }
    @GetMapping("/all")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

}
