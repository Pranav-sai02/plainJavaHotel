package com.neoteric.plainJavaHotel.usingStreams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;



    public CompletableFuture<Location> saveLocationAsync (Location location) {
        return CompletableFuture.supplyAsync(() -> locationRepository.save(location));
    }


}
