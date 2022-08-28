package com.example.accountmanagementtask.controllers;

import com.example.accountmanagementtask.dto.AccountDetails;
import com.example.accountmanagementtask.entity.AccountDetailsEntity;
import com.example.accountmanagementtask.repository.AccountDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountDetailsIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountDetailsRepository repository;


    @Test
    public void testGetAccountDetails() {
        final var accountDetails = createAccountDetails(new BigDecimal("100.00"));

        final var url = String.format("/account/%s", accountDetails.getId().toString());
        final var resp = restTemplate.getForEntity(url, AccountDetails.class);

        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        assertNotNull(resp.getBody());
        assertEquals(accountDetails.getBalance(), resp.getBody().getBalance());
    }

    @Test
    public void shouldReturnNotFound_whenAccountNotFound() {
        final var url = String.format("/account/%s", UUID.randomUUID());
        final var resp = restTemplate.getForEntity(url, AccountDetails.class);

        assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    private AccountDetailsEntity createAccountDetails(BigDecimal value) {
        return repository.save(AccountDetailsEntity.builder()
                .id(UUID.randomUUID())
                .balance(value)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
    }
}
