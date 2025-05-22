package com.neoteric.plainJavaHotel.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelReviewService hotelReviewService;

    @Autowired
    private HotelService hotelService;


    @PostMapping("/addFullHotel")
    public ResponseEntity<String> addFullHotelAsync(@RequestBody HotelFullRequestDTO dto) {
        hotelReviewService.saveFullHotelDetailsAsync(dto);
        return ResponseEntity.accepted().body("Hotel, location, reviews, and images are being saved asynchronously.");
    }


    @PostMapping("/addHotel")
    public CompletableFuture<Hotel> addHotel(@RequestBody Hotel hotel) {
        return hotelReviewService.saveHotelOnlyAsync(hotel);
    }


    @PostMapping("/addLocation")
    public CompletableFuture<Location> addLocation(@RequestBody Location location) {
        return hotelReviewService.saveLocationOnlyAsync(location);
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

