package com.microservices.learning1.controller;

import com.microservices.learning1.constant.AccountConstant;
import com.microservices.learning1.dto.CustomerDto;
import com.microservices.learning1.dto.ResponseDto;
import com.microservices.learning1.service.impl.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST API FOR ACCOUNTS IN EAZY BANK",
        description = "create update delete and fetch account details"
)
public class AccountsController {
    public AccountService accountService;


    @PostMapping
    @Operation(
            description = "Create Account and customer "
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status created"
    )

    public ResponseEntity<ResponseDto>createAccount(@Valid @RequestBody CustomerDto dto){
     accountService.createAccount(dto);
     return  ResponseEntity
             .status(HttpStatus.CREATED)
             .body(new ResponseDto(AccountConstant.STATUS_201,AccountConstant.MESSAGE_201));
    }
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto>fetchAccountDetails(@RequestParam
                                                              @Pattern(regexp = "($|[0-9]{10})",message = "number must have 10 digits")
                                                              String mobileNumber){
        CustomerDto customerDto = accountService.fetchAcounts(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @PutMapping("/updating")
    public ResponseEntity<ResponseDto>updateAccount(@RequestBody CustomerDto dto){
        boolean b = accountService.updateAccount(dto);
        if(b){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200,AccountConstant.MESSAGE_200));
        }else{
            return
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new ResponseDto(AccountConstant.STATUS_500,AccountConstant.MESSAGE_500));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto>deleteAccount( @RequestParam String mobileNumber){
        boolean b = accountService.deleteAccount(mobileNumber);
        if(b){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200,AccountConstant.MESSAGE_200));
        }else{
            return
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDto(AccountConstant.STATUS_500,AccountConstant.MESSAGE_500));
        }
    }
}
