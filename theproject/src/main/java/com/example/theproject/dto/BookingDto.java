package com.example.theproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookingDto {

    private int id;


    @NotBlank(message = "Check in date is mandatory")
    private  String checkIn;

    @NotBlank(message = "Check out date is mandatory")
    private  String checkOut;

    @Min(value = 1, message = "Offer id must be greater than 0")
    private int offerId;

}




