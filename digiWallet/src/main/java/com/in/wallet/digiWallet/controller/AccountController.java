package com.in.wallet.digiWallet.controller;

import com.in.wallet.digiWallet.entity.Account;
import com.in.wallet.digiWallet.service.AccountService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountNumber) {
        Optional<Account> account = accountService.getAccountByNumber(accountNumber);
        return account.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    @PutMapping("/{accountNumber}/edit-name")
    public ResponseEntity<Account> editAccountName(@PathVariable Long accountNumber, @RequestParam String newName) {
        Account updatedAccount = accountService.updateAccountName(accountNumber, newName);
        return ResponseEntity.ok(updatedAccount);
    }

}
