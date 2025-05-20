package com.neoteric.plainJavaHotel.hotelreview;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel getHotelByName(String name) {
        return hotelRepository.findByHotelName(name);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    public Hotel getHotelByNameAndLocation(String hotelName, int locationId) {
        return hotelRepository.findByHotelNameAndLocation_Id(hotelName, locationId);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }


}
