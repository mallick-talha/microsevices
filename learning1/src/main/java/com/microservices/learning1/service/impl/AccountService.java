package com.microservices.learning1.service.impl;

import com.microservices.learning1.constant.AccountConstant;
import com.microservices.learning1.dto.AccountsDto;
import com.microservices.learning1.dto.CustomerDto;
import com.microservices.learning1.entity.Accounts;
import com.microservices.learning1.entity.Customer;
import com.microservices.learning1.exception.CustomerAlreadyExistException;
import com.microservices.learning1.exception.ResourceNotFoundException;
import com.microservices.learning1.mapper.AccountsMapper;
import com.microservices.learning1.mapper.CustomerMapper;
import com.microservices.learning1.repository.AccountRepository;
import com.microservices.learning1.repository.CustomerRepository;
import com.microservices.learning1.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public CustomerDto fetchAcounts(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts byCustomerId = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Accounts","mobileNimber",customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.maptoAccountsDto(byCustomerId,new AccountsDto()));
        return customerDto;

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = true;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).
                    orElseThrow(() -> new ResourceNotFoundException("Account", "accountnumber", accountsDto.getAccountNumber().toString()));

            AccountsMapper.maptoAccounts(accounts, accountsDto);
            accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerNumber", accounts.getAccountNumber().toString()));
            customerRepository.save(customer);
            isUpdated=true;

        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String number) {
        Customer customer = customerRepository.findByMobileNumber(number).
                orElseThrow(() -> new ResourceNotFoundException("customer", "mobilenUmber", number.toString()));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
