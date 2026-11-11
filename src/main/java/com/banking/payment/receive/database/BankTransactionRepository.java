package com.banking.payment.receive.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransactionRepository extends JpaRepository<BankTransactionEntity, Long> {

    boolean existsByReference(String reference);

    Long countByReference(String reference);
}
