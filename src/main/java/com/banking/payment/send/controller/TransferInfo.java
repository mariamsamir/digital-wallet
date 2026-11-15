package com.banking.payment.send.controller;


import lombok.Builder;

import java.util.Date;

@Builder
public record TransferInfo(String reference, Date date, Double amount, String currency) {
}
