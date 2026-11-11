package com.banking.payment.send.domain;


import java.util.Date;

public record TransferInfo(String reference, Date date, Double amount, String currency) {
}
