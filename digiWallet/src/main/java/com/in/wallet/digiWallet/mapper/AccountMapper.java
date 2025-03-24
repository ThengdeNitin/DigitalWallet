package com.in.wallet.digiWallet.mapper;

import com.in.wallet.digiWallet.entity.Account;
import com.in.wallet.digiWallet.dto.AccountDto;

public class AccountMapper {

    private AccountMapper() {
        // Private constructor to prevent instantiation
    }

    public static Account mapToAccount(AccountDto accountDto) {
        return new Account(
                accountDto.getId(),
                accountDto.getAccountNumber(),
                accountDto.getAccountHolderName(),
                accountDto.getPassword(),  
                accountDto.getBalance(),
                null  
        );
    }

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getPassword(), 
                account.getBalance(),
                null,
                null
        );
    }
}
