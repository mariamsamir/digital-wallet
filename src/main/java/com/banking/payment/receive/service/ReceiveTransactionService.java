package com.banking.payment.receive.service;

import com.banking.payment.receive.database.BankTransactionEntity;
import com.banking.payment.receive.database.BankTransactionRepository;
import com.banking.payment.receive.domain.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiveTransactionService {

    private final BankTransactionRepository transactionRepository;

    @Transactional
    public void receive(Transaction transaction, Long clientId) {
        if(!transactionRepository.existsByReference(transaction.getReference())) {
            BankTransactionEntity transactionEntity = transaction.toEntity();
            transactionEntity.setClientId(clientId);
            transactionRepository.save(transaction.toEntity());
        }
    }
}
