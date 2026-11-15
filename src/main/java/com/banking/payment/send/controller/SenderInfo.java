package com.banking.payment.send.controller;

import lombok.Builder;

@Builder
public record SenderInfo(String accountNumber) {
}
