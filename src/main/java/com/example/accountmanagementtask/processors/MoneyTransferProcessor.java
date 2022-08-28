package com.example.accountmanagementtask.processors;

import com.example.accountmanagementtask.dto.MoneyTransferRequest;
import com.example.accountmanagementtask.entity.AccountDetailsEntity;
import com.example.accountmanagementtask.entity.MoneyTransferEntity;
import com.example.accountmanagementtask.exceptions.NotEnoughBalanceException;
import com.example.accountmanagementtask.exceptions.TransferFailedException;
import com.example.accountmanagementtask.services.AccountDetailsService;
import com.example.accountmanagementtask.services.MoneyTransferService;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MoneyTransferProcessor {

    private final AccountDetailsService accountDetailsService;
    private final MoneyTransferService moneyTransferService;

    @Transactional
    public void process(MoneyTransferRequest request) {
        AccountDetailsEntity fromAcc = accountDetailsService.get(UUID.fromString(request.getAccountFrom()));
        AccountDetailsEntity toAcc = accountDetailsService.get(UUID.fromString(request.getAccountTo()));
        MoneyTransferEntity transfer = moneyTransferService.createTransfer(fromAcc, toAcc, request.getValue(), request.getRequestId());
        validateFromAccountBalance(fromAcc, request.getValue());
        try {
            doTransfer(fromAcc, toAcc, request.getValue());
        } catch (OptimisticLockException ex) {
            moneyTransferService.failedTransfer(transfer, ex.getMessage());
            throw new TransferFailedException();
        }
        moneyTransferService.completeTransfer(transfer);
    }

    private void doTransfer(AccountDetailsEntity from, AccountDetailsEntity to, BigDecimal value) {
        accountDetailsService.updateBalance(from, from.getBalance().subtract(value));
        accountDetailsService.updateBalance(to, to.getBalance().add(value));
    }

    private void validateFromAccountBalance(AccountDetailsEntity entity, BigDecimal value) {
        if (entity.getBalance().compareTo(value) < 0) {
            throw new NotEnoughBalanceException();
        }
    }
}
