package com.microservices.learning1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
    @NotEmpty(message = "accountNumber can not be empty")
    @Pattern(regexp = "($|[0-9]{10})")
    private Long accountNumber;

    @NotEmpty(message = "account type can not be empty")
    private String accountType;

    @NotEmpty(message = "bracnchAddress can not be empty")
    private String branchAddress;
}
