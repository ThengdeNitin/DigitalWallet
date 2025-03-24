package com.in.wallet.digiWallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private Long accountNumber;
    private String password;
}
