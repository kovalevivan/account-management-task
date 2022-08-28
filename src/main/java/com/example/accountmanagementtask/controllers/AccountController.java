package com.example.accountmanagementtask.controllers;

import com.example.accountmanagementtask.dto.AccountDetails;
import com.example.accountmanagementtask.entity.AccountDetailsEntity;
import com.example.accountmanagementtask.services.AccountDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountDetailsService service;

    @GetMapping("/account/{accountId}")
    public AccountDetails getAccountDetails(@PathVariable String accountId) {
        return toDto(service.get(UUID.fromString(accountId)));
    }

    private AccountDetails toDto(AccountDetailsEntity entity) {
        return AccountDetails.builder()
                .id(entity.getId().toString())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .balance(entity.getBalance())
                .build();
    }
}
