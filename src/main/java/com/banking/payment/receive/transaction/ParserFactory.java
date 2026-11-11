package com.banking.payment.receive.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ParserFactory {

    private final Map<String, TransactionParser> transactionParserMap;

    public TransactionParser getParser(BankType bankType) {
        return transactionParserMap.get(bankType.name());
    }
}
