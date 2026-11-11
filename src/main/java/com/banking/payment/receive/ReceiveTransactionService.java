package com.banking.payment.receive;

import com.banking.payment.receive.database.BankTransactionEntity;
import com.banking.payment.receive.database.BankTransactionRepository;
import com.banking.payment.receive.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
