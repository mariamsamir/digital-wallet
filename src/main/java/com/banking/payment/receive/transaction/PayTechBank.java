package com.banking.payment.receive.transaction;

import com.banking.payment.receive.InvalidTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component("PAY_TECH")
public class PayTechBank implements TransactionParser {


    @Override
    public List<Transaction> parse(String transactionsData) throws InvalidTransactionException {
        List<String> data = List.of(transactionsData.split("\\n"));

        return data.stream().map(this::buildTransaction).collect(Collectors.toList());
    }

    private Transaction buildTransaction(String data) {
        try {
            String[] dataList = data.split("[,#]+");

//            Date date = Date.valueOf(dataList[0]);
            Double amount = Double.valueOf(dataList[1]);
            String reference = dataList[2];
            Map<String, String> notes = new HashMap<>();

            if(dataList.length >= 4) {
                String[] notesList = dataList[3].split("/");

                for (int i = 0; i < notesList.length; i += 2) {
                    notes.put(notesList[i], notesList[i + 1]);
                }
            }

            return Transaction.builder()
                    .reference(reference)
                    .date(dataList[0])
                    .amount(amount)
                    .notes(notes)
                    .build();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InvalidTransactionException("Invalid data transaction");
        }
    }
}
