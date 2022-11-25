package com.example.springsecuritydemo.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                                .loginProcessingUrl("/login")
//                        .loginPage("/login") //カスタムのログインページを指定する場合
                                .defaultSuccessUrl("/top")
                                .failureUrl("/login?error")
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                ).authorizeHttpRequests(authz -> authz
                        // css/**などのstaticなファイルは認証不要
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // /sampleは認証不要
                        .requestMatchers("/sample").permitAll()
                        // ロール設定
                        .requestMatchers("/general").hasRole("GENERAL")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // 上記以外は要認証
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
