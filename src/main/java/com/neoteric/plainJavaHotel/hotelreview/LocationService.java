package com.neoteric.plainJavaHotel.hotelreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getLocationsByLatitudeAndLongitude(float latitude, float longitude) {
        return locationRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocationById(int id) {
        return locationRepository.findById(id).orElse(null);
    }


}
