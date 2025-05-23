package com.neoteric.plainJavaHotel.usingStreams;

import org.springframework.beans.factory.annotation.Autowired;
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

    public CompletableFuture<Hotel> saveHotelAsync(Hotel hotel) {
        return CompletableFuture.supplyAsync(() -> hotelRepository.save(hotel));
    }

}
