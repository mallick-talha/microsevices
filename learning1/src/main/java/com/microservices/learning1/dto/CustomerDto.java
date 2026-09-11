package com.microservices.learning1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotEmpty(message = "name can not be null")
    @Size(min = 4,max = 30,message = "The length of the customer should be atleast 5 and")
    private String name;
    @NotEmpty(message = "email can not be empty")
    @Email(message = "Email should have valid value don't forget to add @ while giveing your email id")
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile no mush have 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
