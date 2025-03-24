package com.in.wallet.digiWallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.in.wallet.digiWallet.entity.Transaction;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_AccountNumber(Long accountNumber);
}
