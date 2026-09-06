package com.microservices.learning1.controller;

import com.microservices.learning1.constant.AccountConstant;
import com.microservices.learning1.dto.CustomerDto;
import com.microservices.learning1.dto.ResponseDto;
import com.microservices.learning1.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountsController {
    public AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseDto>createAccount(@RequestBody CustomerDto dto){
     accountService.createAccount(dto);
     return  ResponseEntity
             .status(HttpStatus.CREATED)
             .body(new ResponseDto(AccountConstant.STATUS_201,AccountConstant.MESSAGE_201));
    }
}
