package com.banking.payment.receive.transaction;

import com.banking.payment.receive.database.BankTransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class Transaction {

    private String reference;

    private String date;

    private Double amount;

    private Map<String, String> notes;

    public BankTransactionEntity toEntity() {
       return  BankTransactionEntity.builder()
                .amount(this.amount)
                .notes(this.notes)
                .reference(this.reference)
                .date(this.date)
                .build();
    }
}
