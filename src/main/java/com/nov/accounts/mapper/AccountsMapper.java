package com.nov.accounts.mapper;

import com.nov.accounts.dto.AccountsDto;
import com.nov.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto maptoAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;

    }

    public static Accounts mapToAccounts(Accounts   accounts, AccountsDto accountsDto) {
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
