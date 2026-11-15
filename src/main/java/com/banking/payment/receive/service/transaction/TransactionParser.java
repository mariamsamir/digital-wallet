package com.banking.payment.receive.service.transaction;

import com.banking.payment.receive.domain.Transaction;
import com.banking.payment.receive.controller.InvalidTransactionException;

import java.util.List;

public interface TransactionParser {

    List<Transaction> parse(String transactionsData) throws InvalidTransactionException;
}
