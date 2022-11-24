package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.mbgenerate.entity.LoginUserAndRoleNameEntity;
import com.example.springsecuritydemo.record.LoginUserDto;
import com.example.springsecuritydemo.repository.LoginUserRepository;
import com.example.springsecuritydemo.security.UserDetailsImpl;
import com.example.springsecuritydemo.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class UserDetailsServiceImplTest {
    private static AutoCloseable closeable;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Mock
    private LoginUserRepository loginUserRepository;

    @AfterAll
    public static void end() throws Exception {
        closeable.close();
    }

    @BeforeEach
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Nested
    class LoadUserByUsername {
        @Test
        @DisplayName("正常系")
        public void success() throws Exception {
            doReturn(List.of(LoginUserAndRoleNameEntity.builder()
                    .name("admin")
                    .email("admin@example.com")
                    .password("$2a$10$SJTWvNl16fCU7DaXtWC0DeN/A8IOakpCkWWNZ/FKRV2CHvWElQwMS")
                    .roleName("ROLE_ADMIN")
                    .build())
            ).when(loginUserRepository).selectLoginUserAndRoleNameRecordByEmail("admin@example.com");

            final var expected = new UserDetailsImpl(
                    new LoginUserDto("admin@example.com",
                            "admin",
                            "$2a$10$SJTWvNl16fCU7DaXtWC0DeN/A8IOakpCkWWNZ/FKRV2CHvWElQwMS",
                            List.of("ROLE_ADMIN")
                    )
            );

            final var actual = userDetailsServiceImpl.loadUserByUsername("admin@example.com");
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }
}
