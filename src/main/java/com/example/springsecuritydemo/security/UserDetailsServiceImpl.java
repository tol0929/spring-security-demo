package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.mbgenerate.entity.LoginUserAndRoleNameEntity;
import com.example.springsecuritydemo.record.LoginUserDto;
import com.example.springsecuritydemo.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final LoginUserRepository loginUserRepository;

    /**
     * ログインユーザー読み込み
     *
     * @param username 画面から渡されるlogin_user.email
     * @return UserDetailsImpl
     *
     * @throws UsernameNotFoundException ユーザーが見つからない場合
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final var loginUserAndRoleNameEntityList = loginUserRepository.selectLoginUserAndRoleNameRecordByEmail(username);
        if (CollectionUtils.isEmpty(loginUserAndRoleNameEntityList)) {
            throw new UsernameNotFoundException("login user not found.");
        }

        final var loginUserAndRoleEntity = loginUserAndRoleNameEntityList.get(0);
        final var roles = loginUserAndRoleNameEntityList.stream().map(LoginUserAndRoleNameEntity::getRoleName).toList();

        return new UserDetailsImpl(
                new LoginUserDto(loginUserAndRoleEntity.getEmail(),
                        loginUserAndRoleEntity.getName(),
                        loginUserAndRoleEntity.getPassword(),
                        roles
                )
        );
    }
}
