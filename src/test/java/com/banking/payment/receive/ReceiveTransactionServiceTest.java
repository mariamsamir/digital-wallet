package com.banking.payment.receive;


import com.banking.payment.receive.database.BankTransactionRepository;
import com.banking.payment.receive.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReceiveTransactionServiceTest {

    @Autowired
    private ReceiveTransactionService receiveTransactionService;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Test
//    @Rollback
    void receive(){

        Transaction transaction = Transaction.builder()
                .amount(1000.0)
                .reference("98745662125665")
                .date(LocalDateTime.now().toString())
                .build();

        receiveTransactionService.receive(transaction, 5L);

        boolean exists = bankTransactionRepository.existsByReference(transaction.getReference());

        assertTrue( exists);

    }

    @Test
//    @Rollback
    void receiveDuplicateTransaction(){

        Transaction transaction = Transaction.builder()
                .amount(1000.0)
                .reference("98745662125665")
                .date(LocalDateTime.now().toString())
                .build();

        receiveTransactionService.receive(transaction, 5L);
        receiveTransactionService.receive(transaction, 5L);

        boolean exists = bankTransactionRepository.existsByReference(transaction.getReference());

        assertTrue( exists);

        Long count = bankTransactionRepository.countByReference(transaction.getReference());

        assertEquals(1, count);

    }

}