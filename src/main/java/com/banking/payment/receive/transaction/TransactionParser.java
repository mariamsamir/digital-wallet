package com.banking.payment.receive.transaction;

import com.banking.payment.receive.InvalidTransactionException;

import java.util.List;

public interface TransactionParser {

    List<Transaction> parse(String transactionsData) throws InvalidTransactionException;
}
