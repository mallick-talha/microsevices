package com.microservices.learning1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {

    private String name;
    
    private String email;

    private String mobileNumber;
}
