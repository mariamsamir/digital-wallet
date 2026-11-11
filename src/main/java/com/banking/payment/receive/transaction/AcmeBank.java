package com.banking.payment.receive.transaction;

import com.banking.payment.receive.InvalidTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("ACME")
public class AcmeBank implements TransactionParser {


    @Override
    public List<Transaction> parse(String transactionsData) throws InvalidTransactionException {
        List<String> data = List.of(transactionsData.split("\\n"));

        return data.stream().map(this::buildTransaction).collect(Collectors.toList());
    }

    private Transaction buildTransaction(String data) {
        try {
            String[] dataList = data.split("[/+]+");


            Double amount = Double.valueOf(dataList[0].replace(",","."));
            String reference = dataList[1];
//            LocalDateTime date = LocalDateTime.parse(dataList[2]);

            return Transaction.builder()
                    .reference(reference)
                    .date(dataList[2])
                    .amount(amount)
                    .build();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InvalidTransactionException("Invalid data transaction");
        }
    }
}
