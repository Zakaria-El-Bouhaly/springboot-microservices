package com.example.theproject.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OfferDto {

    private int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;
    private String city;

    @Min(value = 0, message = "Price must be positive")
    private Double price;



    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Phone number must be valid")
    private String phoneNumber;


    @NotBlank(message = "User id is mandatory")
    @Min(value = 1, message = "User id must be positive")
    private String userId;

    @NotBlank(message = "Latitude is mandatory")
    @Min(value = -90, message = "Latitude must be between -90 and 90")
    private String latitude;

    @NotBlank(message = "Longitude is mandatory")
    @Min(value = -180, message = "Longitude must be between -180 and 180")
    private String longitude;

}




