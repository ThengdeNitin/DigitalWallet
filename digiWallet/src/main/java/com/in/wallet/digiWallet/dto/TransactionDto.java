package com.in.wallet.digiWallet.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;
    private Long accountNumber;
    private Double depositAmount;
    private Double withdrawAmount;
    private Double balance;
    private LocalDateTime dateTime;
}
