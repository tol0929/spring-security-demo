package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.General;
import com.example.springsecuritydemo.mbgenerate.entity.LoginUserAndRoleNameEntity;
import com.example.springsecuritydemo.repository.LoginUserRepository;
import com.example.springsecuritydemo.security.SecurityConfig;
import com.example.springsecuritydemo.security.UserDetailsServiceImpl;
import com.example.springsecuritydemo.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {UserDetailsServiceImpl.class,
                SecurityConfig.class,
        }
)
)
public class GeneralControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private LoginUserRepository loginUserRepository;
    @MockBean
    private SampleService sampleService;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        doReturn(List.of(LoginUserAndRoleNameEntity.builder()
                .name("general")
                .email("general@example.com")
                .password("$2a$10$6fPXYK.C9rCWUBifuqBIB.GRNU.nQtBpdzkkKis8ETaKVKxNo/ltO")
                .roleName("ROLE_GENERAL")
                .build())
        ).when(loginUserRepository).selectLoginUserAndRoleNameRecordByEmail("general@example.com");
    }

    @Test
    @General
    @DisplayName("ROLE_GENERALが一般向け画面にアクセスできること")
    public void generalTest() throws Exception {
        mockMvc.perform(get("/general"))
                .andExpect(status().isOk())
                .andExpect(view().name("general"));
    }

}
