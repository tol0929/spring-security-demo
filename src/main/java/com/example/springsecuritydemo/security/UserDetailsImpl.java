package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.record.LoginUserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    @Getter
    private final LoginUserDto loginUser;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(final LoginUserDto loginUser) {
        this.loginUser = loginUser;
        this.authorities = loginUser.roleList()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // ハッシュ化済パスワードを返す
    @Override
    public String getPassword() {
        return loginUser.password();
    }

    @Override
    public String getUsername() {
        return loginUser.name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
