package com.example.person.controller;

import com.example.person.dao.PersonRepository;
import com.example.person.model.Person;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {
    private static final Logger logger = LogManager.getLogger("Test");
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonRepository personRepository;

    Person person = new Person(1L, "John Doe", "45 Clark St Southbank",
            "3006", "30", "Developer", "johndoe@gmail.com", "0498582854");
    Person person_new = new Person(1L, "Alex Beth", "45 Clark St Southbank",
            "3006", "30", "Developer", "alexbeth@gmail.com", "0498582854");

    @Test
    void createPerson_returns202_IfCreated() throws Exception {
        Mockito.when(personRepository.save(person)).thenReturn(person);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.post(
                                "/person").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person));

        mockMvc.perform(mockRequest)
                .andExpect(status().isAccepted());
    }

    @Test
    void findPersonById_returns202_IfFound() throws Exception {
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.ofNullable(person));

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.get(
                                "/person1").
                        contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name", Matchers.is("John Doe")));
    }

    @Test
    void updatePerson_returns202_IfUpdated() throws Exception {
        Mockito.when(personRepository.save(person_new)).thenReturn(person_new);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.put(
                                "/person").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person_new));

        mockMvc.perform(mockRequest)
                .andExpect(status().isAccepted());
    }

}
