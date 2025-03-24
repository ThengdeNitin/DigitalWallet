package com.in.wallet.digiWallet.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.in.wallet.digiWallet.dto.AccountDto;
import com.in.wallet.digiWallet.mapper.AccountMapper;
import com.in.wallet.digiWallet.repository.AccountRepository;
import com.in.wallet.digiWallet.entity.Account;

@Service
public class AccountServiceImpl {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

 
    public AccountDto getAccountById(Long accountNumber) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }
    

    public Account updateAccountName(Long accountNumber, String newName) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setAccountHolderName(newName);
        return accountRepository.save(account);
    }

   
    public Account updateBalance(Long accountNumber, Double newBalance) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    @Transactional
    public AccountDto deposit(Long accountNumber, double amount) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }

    @Transactional
    public AccountDto withdraw(Long accountNumber, double amount) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }
}
