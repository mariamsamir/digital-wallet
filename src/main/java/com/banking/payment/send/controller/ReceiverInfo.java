package com.banking.payment.send.controller;

import lombok.Builder;

@Builder
public record ReceiverInfo(String bankCode, String accountNumber, String beneficiaryName) {
}