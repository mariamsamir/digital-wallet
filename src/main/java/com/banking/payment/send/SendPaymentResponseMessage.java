package com.banking.payment.send;

import com.banking.payment.send.domain.ReceiverInfo;
import com.banking.payment.send.domain.SenderInfo;
import com.banking.payment.send.domain.TransferInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@Data
@Builder
@JsonRootName("PaymentRequestMessage")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SendPaymentResponseMessage {

    @JacksonXmlElementWrapper(localName = "TransferInfo")
    TransferInfo transferInfo;

    @JacksonXmlElementWrapper(localName = "SenderInfo")
    SenderInfo senderInfo;

    @JacksonXmlElementWrapper(localName = "ReceiverInfo")
    ReceiverInfo receiverInfo;

    @JacksonXmlElementWrapper(localName = "Notes")
    @JacksonXmlProperty(localName = "Note")
    List<String> notes;

    @JacksonXmlElementWrapper(localName = "PaymentType")
    String paymentType;

    @JacksonXmlElementWrapper(localName = "ChargeDetails")
    String chargeDetails;
}
