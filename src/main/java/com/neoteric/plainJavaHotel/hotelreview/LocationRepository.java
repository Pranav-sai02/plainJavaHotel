package com.neoteric.plainJavaHotel.hotelreview;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByLatitudeAndLongitude(float latitude, float longitude);
    Location findByName(String name);
}

