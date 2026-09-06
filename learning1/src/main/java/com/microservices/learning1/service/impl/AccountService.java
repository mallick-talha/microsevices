package com.microservices.learning1.service.impl;

import com.microservices.learning1.constant.AccountConstant;
import com.microservices.learning1.dto.CustomerDto;
import com.microservices.learning1.entity.Accounts;
import com.microservices.learning1.entity.Customer;
import com.microservices.learning1.exception.CustomerAlreadyExistException;
import com.microservices.learning1.mapper.CustomerMapper;
import com.microservices.learning1.repository.AccountRepository;
import com.microservices.learning1.repository.CustomerRepository;
import com.microservices.learning1.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
@Service
@AllArgsConstructor
public class AccountService implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(byMobileNumber.isPresent()){
            throw new CustomerAlreadyExistException("Customer is already registered with given mobile number"+customer.getMobileNumber());
        }
        customer.setCreatedAt(LocalDate.now());
        customer.setCreatedBy(customer.getName());
        customerRepository.save(customer);
        accountRepository.save(createNewAccount(customer));
    }
    public Accounts createNewAccount(Customer customer){
        Accounts newAccount= new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        Long randomAccountNumber= 1000000000L+new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstant.SAVINGS);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);
        return newAccount;


    }
}
