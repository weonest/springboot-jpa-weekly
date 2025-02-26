package com.jpaweekly.domain.user.application;

import com.jpaweekly.domain.user.User;
import com.jpaweekly.domain.user.dto.UserCreateRequest;
import com.jpaweekly.domain.user.infrastructrue.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long createUser(UserCreateRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.builder()
                .loginId(request.loginId())
                .password(encodedPassword)
                .nickname(request.nickname())
                .build();
        userRepository.save(user);
        return user.getId();
    }
}
