package com.microservices.learning1.service;

import com.microservices.learning1.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAcounts(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String number);
}
