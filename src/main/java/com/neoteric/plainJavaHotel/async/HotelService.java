package com.neoteric.plainJavaHotel.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Async
    public CompletableFuture<Hotel> saveHotelAsync(Hotel hotel) {
        Hotel saved = hotelRepository.save(hotel);
        return CompletableFuture.completedFuture(saved);
    }
}
