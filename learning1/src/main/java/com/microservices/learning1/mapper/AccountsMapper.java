package com.microservices.learning1.mapper;

import com.microservices.learning1.dto.AccountsDto;
import com.microservices.learning1.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto maptoAccountsDto(Accounts accounts,AccountsDto accountsDto){
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }
   public static Accounts maptoAccounts(Accounts accounts,AccountsDto accountsDto){
    accounts.setAccountNumber(accountsDto.getAccountNumber());
    accounts.setAccountType(accountsDto.getAccountType());
    accounts.setBranchAddress(accountsDto.getBranchAddress());
    return accounts;
}
}
