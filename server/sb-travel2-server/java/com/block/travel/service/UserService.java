package com.block.travel.service;

import com.block.travel.config.JwtConfig;
import com.block.travel.dto.LoginResponse;
import com.block.travel.dto.UserRequest;
import com.block.travel.entity.User;
import com.block.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtConfig jwtConfig;
    
    public LoginResponse login(UserRequest userRequest){
        // 1. DB에서 회원 정보를 가져온다.
        User user = userRepository.findByEmail(userRequest.email);
        // 2. 비밀번호 체크
        if( !passwordEncoder.matches(userRequest.password, user.password) )
        {
            throw new RuntimeException();
        }
        // 3. 토큰 생성
        String token = jwtConfig.createToken(user.id);
        LoginResponse loginResponse = new LoginResponse(token);
        return loginResponse;
    }
    

    public void signUp(UserRequest userRequest){
        // 1. 비번을 암호화
        userRequest.password =
                passwordEncoder.encode(userRequest.password);

        // 2. DB에 저장
        // DTO 를 Entity로 저장
        User user = new User();
        user.email = userRequest.email;
        user.password = userRequest.password;
        user.nickname = userRequest.nickname;

        userRepository.save(user);
    }
}
