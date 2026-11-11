package com.banking.payment.receive;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,
        reason = "Invalid Transaction Data!")
public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String message) {
        super(message);
    }
}
