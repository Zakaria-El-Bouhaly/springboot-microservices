package com.example.theproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SearchOfferDto {
    @NotBlank(message = "city is mandatory")
    private String city;

    @NotBlank(message = "start date is mandatory")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "startDate must be in format yyyy-MM-dd")

    private String startDate;

    @NotBlank(message = "end date is mandatory")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "endDate must be in format yyyy-MM-dd")
    private String endDate;


}




