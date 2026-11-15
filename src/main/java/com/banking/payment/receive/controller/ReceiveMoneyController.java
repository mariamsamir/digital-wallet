package com.banking.payment.receive.controller;

import com.banking.payment.receive.service.ReceiveTransactionService;
import com.banking.payment.receive.service.transaction.ParserFactory;
import com.banking.payment.receive.service.transaction.BankType;
import com.banking.payment.receive.domain.Transaction;
import com.banking.payment.receive.service.transaction.TransactionParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment/receive")
@RequiredArgsConstructor
public class ReceiveMoneyController {


    private final ParserFactory transactionConfiguration;
    private final ReceiveTransactionService receiveTransactionService;

    @PostMapping("/{clientId}/{bankId}")
    public ResponseEntity<Void>  receive(
                         @PathVariable("clientId") Long clientId,
                         @PathVariable("bankId") String bankId,
                         @RequestBody String transactions) {

        try {
            TransactionParser transactionParser = transactionConfiguration.getParser(BankType.valueOf(bankId));
            List<Transaction> transactionList = transactionParser.parse(transactions);
            transactionList.forEach(transaction -> {
                log.info("Save Transaction By Reference: {}", transaction.getReference());
                receiveTransactionService.receive(transaction, clientId);
            });
        } catch (Exception e) {
            throw new InvalidTransactionException("Invalid Transaction Data!");
        }

        return ResponseEntity.ok().build();
    }
}
