package com.example.account.controller;


import com.example.account.dao.AccountRepository;
import com.example.account.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {
    private static final Logger logger = LogManager.getLogger("Test");
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountRepository accountRepository;

    Account account = new Account(23456789L, 1L, "Loan", "John Doe", "300",
            "10102022");
    Account account_new = new Account(23456789L, 1L, "Saving", "John Doe",
            "300",
            "10102022");

    @Test
    void createAccount_returns202_IfCreated() throws Exception {
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.post(
                                "/account").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account));

        mockMvc.perform(mockRequest)
                .andExpect(status().isAccepted());
    }

    @Test
    void findAccountByAccountNumber_returns202_IfFound() throws Exception {
        Mockito.when(accountRepository.findByAccountNumber(23456789L)).thenReturn(Optional.ofNullable(account));

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.get(
                                "/account23456789").
                        contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.accountName", Matchers.is("John Doe")));
    }

    @Test
    void updatePerson_returns202_IfUpdated() throws Exception {
        Mockito.when(accountRepository.save(account_new)).thenReturn(account_new);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.put(
                                "/account").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account_new));

        mockMvc.perform(mockRequest)
                .andExpect(status().isAccepted());
    }

}

