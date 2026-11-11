package com.banking.payment.send;

import com.banking.payment.send.domain.SendPaymentRequestMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/payment/send",
        produces = "application/xml")
public class SendMoneyController {

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<SendPaymentResponseMessage> sendMoney(@RequestBody SendPaymentRequestMessage requestMessage) {
        return ResponseEntity.ok(SendPaymentResponseMessage.builder()
                .transferInfo(requestMessage.transferInfo())
                .senderInfo(requestMessage.senderInfo())
                .receiverInfo(requestMessage.receiverInfo())
                .notes(requestMessage.notes())
                .paymentType("99".equals(requestMessage.paymentType()) ? requestMessage.paymentType() : null)
                .chargeDetails("SHA".equals(requestMessage.chargeDetails()) ? requestMessage.chargeDetails() : null)
                .build());
    }

}
