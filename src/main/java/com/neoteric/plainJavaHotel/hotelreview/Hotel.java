package com.neoteric.plainJavaHotel.hotelreview;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double latitude;

    private double longitude;

    private String hotelName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviews;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Image> images;




}
