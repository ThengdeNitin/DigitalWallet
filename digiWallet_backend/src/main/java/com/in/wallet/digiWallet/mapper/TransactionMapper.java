package com.in.wallet.digiWallet.mapper;

import com.in.wallet.digiWallet.entity.Transaction;
import com.in.wallet.digiWallet.dto.TransactionDto;
import com.in.wallet.digiWallet.entity.Account;

public class TransactionMapper {

    private TransactionMapper() {
        // Private constructor to prevent instantiation
    }

    public static Transaction mapToTransaction(TransactionDto transactionDto, Account account) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account); // Set the account entity
        transaction.setDepositAmount(transactionDto.getDepositAmount());
        transaction.setWithdrawAmount(transactionDto.getWithdrawAmount());
        transaction.setDateTime(transactionDto.getDateTime());
        return transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                (transaction.getAccount() != null) ? transaction.getAccount().getAccountNumber() : null,
                transaction.getDepositAmount(),
                transaction.getWithdrawAmount(),
                null, transaction.getDateTime()
        );
    }
}
