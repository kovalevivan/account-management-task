package com.example.accountmanagementtask.controllers;

import com.example.accountmanagementtask.dto.MoneyTransferRequest;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoneyTransferIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountDetailsRepository repository;

    @Test
    public void testMoneyTransfer() {
        final var fromAccDet = createAccountDetails(new BigDecimal(100));
        final var toAccDet = createAccountDetails(new BigDecimal(100));

        final var request = MoneyTransferRequest.builder()
                .requestId("123afaf")
                .accountFrom(fromAccDet.getId().toString())
                .accountTo(toAccDet.getId().toString())
                .value(new BigDecimal(10))
                .build();

        final var resp = restTemplate.postForEntity("/transfer", request, MoneyTransferRequest.class);

        assertEquals(resp.getStatusCode(), HttpStatus.OK);

        assertEquals(new BigDecimal("90.00"), repository.findById(fromAccDet.getId()).get().getBalance());
        assertEquals(new BigDecimal("110.00"), repository.findById(toAccDet.getId()).get().getBalance());
    }

    @Test
    public void shouldReturnNotFound_whenAccountNotFound() {
        final var fromAccDet = createAccountDetails(new BigDecimal(100));

        final var request = MoneyTransferRequest.builder()
                .requestId("123afaf")
                .accountFrom(fromAccDet.getId().toString())
                .accountTo(UUID.randomUUID().toString())
                .value(new BigDecimal(10))
                .build();

        final var resp = restTemplate.postForEntity("/transfer", request, MoneyTransferRequest.class);

        assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldReturnBadRequest_whenFromAccountNotHaveEnoughMoney() {
        final var fromAccDet = createAccountDetails(new BigDecimal(100));
        final var toAccDet = createAccountDetails(new BigDecimal(100));

        final var request = MoneyTransferRequest.builder()
                .requestId("123afaf")
                .accountFrom(fromAccDet.getId().toString())
                .accountTo(toAccDet.getId().toString())
                .value(new BigDecimal(200))
                .build();

        final var resp = restTemplate.postForEntity("/transfer", request, MoneyTransferRequest.class);

        assertEquals(resp.getStatusCode(), HttpStatus.BAD_REQUEST);
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
