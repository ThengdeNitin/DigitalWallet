package com.in.wallet.digiWallet.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.wallet.digiWallet.dto.LoginRequestDto;
import com.in.wallet.digiWallet.service.AccountService;
import com.in.wallet.digiWallet.entity.Account;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        Optional<Account> optionalAccount = accountService.getAccountByNumber(loginRequest.getAccountNumber());

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            if (account.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
    }
}
