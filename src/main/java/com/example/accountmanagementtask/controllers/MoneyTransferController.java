package com.example.accountmanagementtask.controllers;

import com.example.accountmanagementtask.dto.MoneyTransferRequest;
import com.example.accountmanagementtask.processors.MoneyTransferProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MoneyTransferController {

    private final MoneyTransferProcessor processor;

    @PostMapping("/transfer")
    public void moneyTransfer(@RequestBody MoneyTransferRequest moneyTransferRequest) {
        processor.process(moneyTransferRequest);
    }
}
