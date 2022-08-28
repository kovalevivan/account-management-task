package com.example.accountmanagementtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyTransferEntity {

    @Id
    private UUID id;
    private UUID fromAccount;
    private UUID toAccount;
    private BigDecimal moneyTransferValue;
    private String requestId;
    private MoneyTransferStatus status;
    private String failedReason;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
}
