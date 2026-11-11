package com.banking.payment.receive;

import com.banking.payment.receive.transaction.ParserFactory;
import com.banking.payment.receive.transaction.BankType;
import com.banking.payment.receive.transaction.Transaction;
import com.banking.payment.receive.transaction.TransactionParser;
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
    public ResponseEntity  receive(
                         @PathVariable("clientId") Long clientId,
                         @PathVariable("bankId") String bankId,
                         @RequestBody String transactions) {

        // parse transactions
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
