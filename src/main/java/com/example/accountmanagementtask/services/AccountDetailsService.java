package com.example.accountmanagementtask.services;

import com.example.accountmanagementtask.entity.AccountDetailsEntity;
import com.example.accountmanagementtask.exceptions.AccountNotFoundException;
import com.example.accountmanagementtask.repository.AccountDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountDetailsService {

    private final AccountDetailsRepository repository;

    public AccountDetailsEntity get(UUID id) {
        Optional<AccountDetailsEntity> optAcc = repository.findById(id);

        if (optAcc.isEmpty()) {
            throw new AccountNotFoundException();
        }

        return optAcc.get();
    }

    public void updateBalance(AccountDetailsEntity accountDetails, BigDecimal newBalance) {
        accountDetails.setBalance(newBalance);
        accountDetails.setUpdatedDate(LocalDateTime.now());
        repository.save(accountDetails);
    }
}
