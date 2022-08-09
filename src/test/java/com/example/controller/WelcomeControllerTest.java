package com.example.controller;

import com.example.service.WelcomeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WelcomeService service;

    @Test
    public void helloworldShouldReturnDefaultMessage() throws Exception {
        this.mockMvc
                .perform(get("/helloworld"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Hello Stranger")));
    }

    @Test
    public void helloworldShouldReturnMessageFromService() throws Exception {
        when(service.helloworldByName(anyString())).thenReturn("Hello Fatemeh Kamyab");

        this.mockMvc
                .perform(post("/helloworld?name=FatemehKamyab"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Hello Fatemeh Kamyab")));
    }

    @Test
    public void authorShouldReturnDefaultMessage() throws Exception {
        this.mockMvc
                .perform(get("/author"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Fatemeh Kamyab")));
    }
}