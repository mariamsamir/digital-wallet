package com.banking.payment.send.domain;

import java.util.List;

public record SendPaymentRequestMessage(TransferInfo transferInfo,
                                        SenderInfo senderInfo,
                                        ReceiverInfo receiverInfo,
                                        List<String> notes,
                                        String paymentType,
                                        String chargeDetails) {
}
