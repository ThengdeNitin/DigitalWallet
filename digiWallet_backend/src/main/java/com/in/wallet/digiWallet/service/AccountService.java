package com.in.wallet.digiWallet.service;

import com.in.wallet.digiWallet.entity.Account;
import com.in.wallet.digiWallet.repository.AccountRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountByNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    public Account updateAccountName(Long accountNumber, String newName) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setAccountHolderName(newName);
        return accountRepository.save(account);
    }
    
    public Account authenticate(Long accountNumber, String password) {
        return accountRepository.findByAccountNumberAndPassword(accountNumber, password)
                .orElse(null);
    }
    
}

