package com.banking.payment.send;

import com.banking.payment.send.controller.SendMoneyController;
import com.banking.payment.send.controller.ReceiverInfo;
import com.banking.payment.send.controller.SendPaymentRequestMessage;
import com.banking.payment.send.controller.SenderInfo;
import com.banking.payment.send.controller.TransferInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(SendMoneyController.class)
class SendMoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sendMoney() throws Exception {
        // Given
        SendPaymentRequestMessage message = SendPaymentRequestMessage.builder()
                .transferInfo(TransferInfo.builder()
                        .amount(1000.70)
                        .currency("EGP")
                        .date(new Date())
                        .reference("465256465548665")
                        .build())
                .receiverInfo(ReceiverInfo.builder()
                        .accountNumber("A123123123")
                        .bankCode("ABC")
                        .beneficiaryName("ABC")
                        .build())
                .senderInfo(SenderInfo.builder()
                        .accountNumber("VBE232323")
                        .build())
                .notes(List.of("aredddf", "dvdfvd"))
                .paymentType("")
                .chargeDetails("BR")
                .build();
        MvcResult result = mockMvc.perform(post("/api/v1/payment/send")
                        .accept(MediaType.APPLICATION_XML_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(message)))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_XML_VALUE, response.getContentType());
    }
}