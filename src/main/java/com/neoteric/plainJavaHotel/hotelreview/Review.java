package com.neoteric.plainJavaHotel.hotelreview;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rating;

    private String text;

    private int locationId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;


    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                ", hotel=" + hotel +
                '}';
    }
}
