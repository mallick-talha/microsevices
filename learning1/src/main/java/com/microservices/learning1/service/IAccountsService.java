package com.microservices.learning1.service;

import com.microservices.learning1.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
}
