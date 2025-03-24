package com.in.wallet.digiWallet.controller;

import com.in.wallet.digiWallet.entity.Transaction;
import com.in.wallet.digiWallet.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit/{accountNumber}")
    public ResponseEntity<Transaction> deposit(@PathVariable Long accountNumber, @RequestParam Double amount) {
        return new ResponseEntity<>(transactionService.deposit(accountNumber, amount), HttpStatus.OK);
    }

    @PostMapping("/withdraw/{accountNumber}")
    public ResponseEntity<Transaction> withdraw(@PathVariable Long accountNumber, @RequestParam Double amount) {
        return new ResponseEntity<>(transactionService.withdraw(accountNumber, amount), HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountNumber) {
        return new ResponseEntity<>(transactionService.getTransactions(accountNumber), HttpStatus.OK);
    }
}
