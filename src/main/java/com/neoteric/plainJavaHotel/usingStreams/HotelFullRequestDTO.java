package com.neoteric.plainJavaHotel.usingStreams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelFullRequestDTO {
    private String locationName;
    private double locationLatitude;
    private double locationLongitude;

    private String hotelName;
    private double hotelLatitude;
    private double hotelLongitude;

    private List<ReviewDTO> reviews;
    private List<ImageDTO> images;
}
