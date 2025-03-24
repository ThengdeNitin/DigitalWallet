package com.in.wallet.digiWallet.service;

import com.in.wallet.digiWallet.entity.Transaction;
import com.in.wallet.digiWallet.entity.Account;
import com.in.wallet.digiWallet.repository.TransactionRepository;
import com.in.wallet.digiWallet.repository.AccountRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public Transaction deposit(Long accountNumber, Double amount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setDepositAmount(amount);
            transaction.setBalance(newBalance);
            transaction.setDateTime(LocalDateTime.now());

            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public Transaction withdraw(Long accountNumber, Double amount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }

            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setWithdrawAmount(amount);
            transaction.setBalance(newBalance);
            transaction.setDateTime(LocalDateTime.now());

            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public List<Transaction> getTransactions(Long accountNumber) {
        return transactionRepository.findByAccount_AccountNumber(accountNumber);
    }
}
