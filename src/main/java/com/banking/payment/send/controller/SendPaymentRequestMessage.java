package com.banking.payment.send.controller;

import lombok.Builder;

import java.util.List;


@Builder
public record SendPaymentRequestMessage(TransferInfo transferInfo,
                                        SenderInfo senderInfo,
                                        ReceiverInfo receiverInfo,
                                        List<String> notes,
                                        String paymentType,
                                        String chargeDetails) {
}
