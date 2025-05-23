package com.neoteric.plainJavaHotel.hotelreview;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByLocationIdAndName(int locationId, String name);
}
