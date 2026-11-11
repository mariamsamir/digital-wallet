package com.banking.payment.receive;

import com.banking.payment.receive.transaction.ParserFactory;
import com.banking.payment.receive.transaction.Transaction;
import com.banking.payment.receive.transaction.TransactionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ReceiveMoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final TransactionParser transactionParser = mock(TransactionParser.class);
    private final ReceiveTransactionService receiveTransactionService = mock(ReceiveTransactionService.class);

    @BeforeEach
    void setup() {
        Map<String, TransactionParser> parserMap = new HashMap<>();
        parserMap.put("ACME", transactionParser);
        ParserFactory parserFactory = new ParserFactory(parserMap);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ReceiveMoneyController(parserFactory, receiveTransactionService)).build();
    }

    @Test
    void receiveSuccessResponseWithAcmeBank() throws Exception {


        when(transactionParser.parse(any(String.class))).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/api/v1/payment/receive/{clientId}/{bankId}", 123, "ACME")
                        .contentType(MediaType.TEXT_PLAIN).content("Test"))
                        .andExpect(status().isOk());

        verify(transactionParser, atMostOnce()).parse(any(String.class));

        verify(receiveTransactionService, atMostOnce()).receive(any(Transaction.class),anyLong());

    }

    @Test
    void receiveBadResponseWithInvalidBank() throws Exception {


        mockMvc.perform(post("/api/v1/payment/receive/{clientId}/{bankId}", 123, "")
                        .contentType(MediaType.TEXT_PLAIN).content("Test"))
                .andExpect(status().is4xxClientError());

        verify(transactionParser, atMostOnce()).parse(any(String.class));

        verify(receiveTransactionService, atMost(0)).receive(any(Transaction.class), anyLong());

    }

    @Test
    void receiveBadResponseWithInvalidTransactionFormat() throws Exception {

        when(transactionParser.parse(any(String.class))).thenThrow(InvalidTransactionException.class);

        mockMvc.perform(post("/api/v1/payment/receive/{clientId}/{bankId}", 123, "ACME")
                        .contentType(MediaType.TEXT_PLAIN).content("test"))
                .andExpect(status().isBadRequest());

        verify(transactionParser, atMostOnce()).parse(any(String.class));

        verify(receiveTransactionService, atMost(0)).receive(any(Transaction.class), anyLong());

    }
}