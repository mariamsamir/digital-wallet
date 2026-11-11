package com.banking.payment.send.domain;

public record ReceiverInfo(String bankCode, String accountNumber, String beneficiaryName) {
}