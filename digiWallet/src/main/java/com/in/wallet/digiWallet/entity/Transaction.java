package com.in.wallet.digiWallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "deposit_amount", nullable = false)
    private Double depositAmount = 0.0;

    @Column(name = "withdraw_amount", nullable = false)
    private Double withdrawAmount = 0.0;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "date_time", nullable = false, updatable = false)
    private LocalDateTime dateTime;

    @PrePersist
    protected void onCreate() {
        this.dateTime = LocalDateTime.now();
    }
}
