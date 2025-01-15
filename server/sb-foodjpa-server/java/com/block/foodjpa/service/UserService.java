package com.block.foodjpa.service;

import com.block.foodjpa.config.JwtConfig;
import com.block.foodjpa.dto.LoginResponse;
import com.block.foodjpa.dto.UserRequest;
import com.block.foodjpa.entity.User;
import com.block.foodjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtConfig jwtConfig;

    public LoginResponse login(UserRequest userRequest){
        System.out.println("유저 서비스 1");
        // 회원인지 아닌지 확인
        if( !userRepository.existsByEmail(userRequest.email) ){
            throw new RuntimeException();
        }
        // DB에서 정보를 가져와서 비번을 확인.
        User user = userRepository.findByEmail(userRequest.email);
        if( !passwordEncoder.matches(userRequest.password, user.password) ){
            throw new RuntimeException();
        }
        // 인증토큰 발급
        String token = jwtConfig.createToken(user.id);

        LoginResponse loginResponse = new LoginResponse(token);
        return loginResponse;
    }

    public void signUp(UserRequest userRequest){
        // 이미 회원가입 했는지 확인
        if( userRepository.existsByEmail(userRequest.email) ){
            throw new RuntimeException();
        }
        // 비번을 암호화
        userRequest.password = passwordEncoder.encode(userRequest.password);

        // DB에 저장.
        // DTO 를 entity로 만들어준다.
        User user = new User();
        user.email = userRequest.email;
        user.password = userRequest.password;
        user.nickname = userRequest.nickname;
        user.role = "USER";
        userRepository.save(user);
    }
}
