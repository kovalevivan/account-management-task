package com.example.accountmanagementtask.services;

import com.example.accountmanagementtask.entity.AccountDetailsEntity;
import com.example.accountmanagementtask.entity.MoneyTransferEntity;
import com.example.accountmanagementtask.entity.MoneyTransferStatus;
import com.example.accountmanagementtask.repository.MoneyTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoneyTransferService {

    private final MoneyTransferRepository repository;

    public MoneyTransferEntity createTransfer(AccountDetailsEntity from, AccountDetailsEntity to, BigDecimal value, String requestId) {
        return repository.save(MoneyTransferEntity.builder()
                .id(UUID.randomUUID())
                .fromAccount(from.getId())
                .toAccount(to.getId())
                .moneyTransferValue(value)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .requestId(requestId)
                .status(MoneyTransferStatus.INITIALIZED)
                .build());
    }

    public void completeTransfer(MoneyTransferEntity transfer) {
        transfer.setStatus(MoneyTransferStatus.COMPLETED);
        transfer.setUpdatedDate(LocalDateTime.now());
        repository.save(transfer);
    }

    public void failedTransfer(MoneyTransferEntity transfer, String message) {
        transfer.setStatus(MoneyTransferStatus.FAILED);
        transfer.setUpdatedDate(LocalDateTime.now());
        transfer.setFailedReason(message);
        repository.save(transfer);
    }

}
