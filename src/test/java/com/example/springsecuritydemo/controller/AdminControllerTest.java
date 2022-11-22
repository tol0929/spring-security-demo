package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.Admin;
import com.example.springsecuritydemo.General;
import com.example.springsecuritydemo.mbgenerate.entity.LoginUserAndRoleNameEntity;
import com.example.springsecuritydemo.repository.LoginUserRepository;
import com.example.springsecuritydemo.security.SecurityConfig;
import com.example.springsecuritydemo.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

//TODO DB部分をmockにすればいけるのでは？
@WebMvcTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {UserDetailsServiceImpl.class,
                SecurityConfig.class,
        }
)
)
//@AutoConfigureJdbc
public class AdminControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @MockBean
    private LoginUserRepository loginUserRepository;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Admin
    @DisplayName("ROLE_ADMINが管理者専用画面にアクセスできること")
    public void adminTest() throws Exception {
        doReturn(List.of(LoginUserAndRoleNameEntity.builder()
                .name("admin")
                .email("admin@example.com")
                .password("$2a$10$SJTWvNl16fCU7DaXtWC0DeN/A8IOakpCkWWNZ/FKRV2CHvWElQwMS")
                .roleName("ROLE_ADMIN"))
        ).when(loginUserRepository).selectLoginUserAndRoleNameRecordByEmail("admin@example.com");

//        final var loginUserRepositoryFromCOntext = webApplicationContext.getBean(LoginUserRepository.class);
//        final var res = loginUserRepositoryFromCOntext.selectLoginUserAndRoleNameRecordByEmail("admin@example.com");

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
    @General
    @DisplayName("ROLE_GENERALは管理者専用画面にアクセスできないこと")
    public void generalTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden())
                .andExpect(view().name("error"));
    }
}

