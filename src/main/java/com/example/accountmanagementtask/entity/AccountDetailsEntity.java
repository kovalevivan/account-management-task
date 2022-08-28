package com.example.accountmanagementtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
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
public class AccountDetailsEntity {

    @Id
    private UUID id;
    private BigDecimal balance;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;

    @Version
    private long version;
}
