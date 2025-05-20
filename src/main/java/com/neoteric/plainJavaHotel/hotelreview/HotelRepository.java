package com.neoteric.plainJavaHotel.hotelreview;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByHotelName(String hotelName);
    Hotel findByHotelNameAndLocation_Id(String hotelName, int locationId);
}
