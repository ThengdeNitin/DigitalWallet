package com.in.wallet.digiWallet.repository;

import com.in.wallet.digiWallet.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByAccountNumber(Long accountNumber);
	Optional<Account> findByAccountNumberAndPassword(Long accountNumber, String password);
}
