package com.example.theproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

    private String message;
    private Map<String, String> errors ;

    // constructor message
    public CustomErrorResponse(String message) {
        this.message = message;
    }

}
