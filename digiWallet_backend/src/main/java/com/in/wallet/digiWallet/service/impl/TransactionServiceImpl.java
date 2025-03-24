package com.in.wallet.digiWallet.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.in.wallet.digiWallet.dto.TransactionDto;
import com.in.wallet.digiWallet.entity.Account;
import com.in.wallet.digiWallet.entity.Transaction;
import com.in.wallet.digiWallet.mapper.TransactionMapper;
import com.in.wallet.digiWallet.repository.AccountRepository;
import com.in.wallet.digiWallet.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public TransactionDto deposit(Long accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        // Update account balance
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        // Create and save transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setDepositAmount(amount);
        transaction.setBalance(account.getBalance());
        transaction.setDateTime(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.mapToTransactionDto(savedTransaction);
    }

    @Transactional
    public TransactionDto withdraw(Long accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        // Update account balance
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        // Create and save transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setWithdrawAmount(amount);
        transaction.setBalance(account.getBalance());
        transaction.setDateTime(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.mapToTransactionDto(savedTransaction);
    }
}
