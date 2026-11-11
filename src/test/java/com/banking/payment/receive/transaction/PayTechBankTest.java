package com.banking.payment.receive.transaction;

import com.banking.payment.receive.InvalidTransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayTechBankTest {


    TransactionParser transactionParser;

    @BeforeEach
    void setup() {
        transactionParser = new PayTechBank();
    }
    @Test
    void parseAndReturnTransactionList() {
        // Given
        String transactions = """
                        20250615156,50#202506159000001#note/debt payment march/internal_reference/A462JE81
                        20250615156,50#202506159000001#note/debt payment June/internal_reference/A462JE81
                        20250615156,50#2025061590845461
                        20250615156,50#202506159000001#note/debt payment march/internal_reference/A462JE81
                        """;
        // When
        List<Transaction> list = transactionParser.parse(transactions);
        // Then
        assertNotNull(list);
        assertEquals(4, list.size());
    }

    @Test
    void parseAndThrowInvalidData() {
        // Given
        String transactions = """
                        20250615156#202506159000001#note/debt payment march/internal_reference/A462JE81
                        20250615156,ewwerw#202506159000001#note/debt payment June/internal_reference/A462JE81
                        20250615156,50#2025061590845461
                        234sdf,50#202506159000001#note/debt payment march/internal_reference/A462JE81
                        """;
       assertThrows(InvalidTransactionException.class, () -> {
            transactionParser.parse(transactions);
        });
        assertThrows(InvalidTransactionException.class, () -> transactionParser.parse(transactions));

    }
}