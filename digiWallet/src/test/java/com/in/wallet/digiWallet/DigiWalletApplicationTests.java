package com.in.wallet.digiWallet;

import com.in.wallet.digiWallet.entity.Account;
import com.in.wallet.digiWallet.entity.Transaction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class AccountTest {

    @Test
    void testAccountCreation() {
        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber(123456789L);
        account.setAccountHolderName("Nitin Thengde");
        account.setPassword("Secure@123");
        account.setBalance(50000.0);

        assertEquals(1L, account.getId());
        assertEquals(123456789L, account.getAccountNumber());
        assertEquals("Nitin Thengde", account.getAccountHolderName());
        assertEquals("Secure@123", account.getPassword());
        assertEquals(50000.0, account.getBalance());
    }

    @Test
    void testBalanceUpdate() {
        Account account = new Account();
        account.setBalance(10000.0);

        account.setBalance(account.getBalance() + 5000.0); // Simulating deposit

        assertEquals(15000.0, account.getBalance());
    }
}

class TransactionTest {

    @Test
    void testTransactionCreation() {
        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber(987654321L);
        account.setBalance(50000.0);

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccount(account);
        transaction.setDepositAmount(5000.0);
        transaction.setWithdrawAmount(0.0);
        transaction.setBalance(55000.0);
        transaction.setDateTime(LocalDateTime.now());

        assertEquals(1L, transaction.getId());
        assertEquals(account, transaction.getAccount());
        assertEquals(5000.0, transaction.getDepositAmount());
        assertEquals(0.0, transaction.getWithdrawAmount());
        assertEquals(55000.0, transaction.getBalance());
        assertNotNull(transaction.getDateTime());
    }

    @Test
    void testWithdrawalTransaction() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(20000.0);

        Transaction transaction = new Transaction();
        transaction.setWithdrawAmount(5000.0);
        transaction.setBalance(account.getBalance() - 5000.0);

        assertEquals(15000.0, transaction.getBalance());
    }
}