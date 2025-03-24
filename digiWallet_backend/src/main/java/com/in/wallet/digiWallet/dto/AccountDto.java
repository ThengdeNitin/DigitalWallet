package com.in.wallet.digiWallet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private Long accountNumber;
    private String accountHolderName;
    private String password;
    private Double balance;
    private List<TransactionDto> transactions; 
    private LocalDateTime createdAt;
}

