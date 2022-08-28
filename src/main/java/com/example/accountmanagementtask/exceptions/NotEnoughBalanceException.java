package com.example.accountmanagementtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not enough balance")
public class NotEnoughBalanceException extends RuntimeException {
}
