package com.example.theproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "offers")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Offer {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String name;
    private String description;

    private String phoneNumber;

    private String city;

    private String image;

    private Double price;

    private String latitude;
    private String longitude;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Booking> bookings;


}
