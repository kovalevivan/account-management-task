package com.example.accountmanagementtask.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AccountDetails {

    private String id;
    private BigDecimal balance;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
}
