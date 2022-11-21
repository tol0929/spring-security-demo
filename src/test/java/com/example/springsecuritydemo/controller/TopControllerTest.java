package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


// = @SpringBootTest + @AutoConfigureMockMvc
@WebMvcTest
public class TopControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    SampleService sampleService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @DisplayName("/topにアクセスできること")
    public void topTest() throws Exception {
        mockMvc.perform(get("/top"))
                .andExpect(status().isOk())
                .andExpect(view().name("top"));
    }

//    @Test
//    @DisplayName("/sampleにアクセスできること")
//    public void sampleTest() throws Exception {
//        final var result = mockMvc.perform(get("/sample"));
//        final var response = result.andReturn().getResponse();
//        mockMvc.perform(get("/sample"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("sample"));
//    }
}
