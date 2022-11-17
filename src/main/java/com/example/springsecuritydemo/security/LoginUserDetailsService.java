package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.mbgenerate.entity.LoginUserRolesEntity;
import com.example.springsecuritydemo.mbgenerate.entity.Roles;
import com.example.springsecuritydemo.record.LoginUserRecord;
import com.example.springsecuritydemo.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
    private final LoginUserRepository loginUserRepository;

    /**
     * ログインユーザー読み込み
     *
     * @param username 画面から渡されるlogin_user.id
     * @return login_user
     *
     * @throws UsernameNotFoundException ユーザーが見つからない場合
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final var loginUserRolesEntity = loginUserRepository.selectLoginUserRolesEntityById(Integer.parseInt(username));
        return loginUserRolesEntity.map(this::generateLoginUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("login_user not found."));
    }

    private UserDetails generateLoginUserDetails(final LoginUserRolesEntity loginUserRolesEntity) {
        final var loginUser = loginUserRolesEntity.getLoginUser();
        final var roles = loginUserRolesEntity.getRoles().stream().map(Roles::getName).toList();
        return new LoginUserDetails(
                new LoginUserRecord(loginUser.getEmail(),
                        loginUser.getName(),
                        loginUser.getPassword(),
                        roles
                )
        );
    }
}
