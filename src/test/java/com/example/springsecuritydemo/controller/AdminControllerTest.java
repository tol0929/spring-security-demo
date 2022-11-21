package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.Admin;
import com.example.springsecuritydemo.mbgenerate.crud.CustomLoginUserMapper;
import com.example.springsecuritydemo.mbgenerate.crud.LoginUserMapper;
import com.example.springsecuritydemo.repository.LoginUserRepository;
import com.example.springsecuritydemo.security.SecurityConfig;
import com.example.springsecuritydemo.security.UserDetailsServiceImpl;
import com.example.springsecuritydemo.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//TODO DB部分をmockにすればいけるのでは？
@WebMvcTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {UserDetailsServiceImpl.class,
                LoginUserRepository.class,
                SecurityConfig.class,
                LoginUserMapper.class,
                CustomLoginUserMapper.class
        }
)
)
//@SpringBootTest(classes = AdminControllerTest.class)
@AutoConfigureJdbc
public class AdminControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    SampleService sampleService;
    //    @MockBean
//    LoginUserMapper loginUserMapper;
//    @MockBean
//    CustomLoginUserMapper customLoginUserMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Admin
    @DisplayName("ROLE_ADMINが管理者専用画面にアクセスできること")
    public void adminTest() throws Exception {
        final var actuaL = mockMvc.perform(get("/admin"));
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
//    @General
    @DisplayName("ROLE_GENERALは管理者専用画面にアクセスできないこと")
    public void generalTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden())
                .andExpect(view().name("error"));
    }
}

