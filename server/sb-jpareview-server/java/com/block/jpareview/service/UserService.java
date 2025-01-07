package com.block.jpareview.service;

import com.block.jpareview.config.JwtConfig;
import com.block.jpareview.dto.LoginResponse;
import com.block.jpareview.dto.UserRequest;
import com.block.jpareview.entity.User;
import com.block.jpareview.repository.UserRepository;
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
        // 1. 테이블에 이 이메일로 유저가 있어야 한다. 유저데이터를 가져온다
        User user = userRepository.findByEmail(userRequest.email);
        // 2. 비밀번호가 맞는지 체크한다.
        if( !passwordEncoder.matches(userRequest.password, user.password)){
            throw new RuntimeException();
        }
        // 3. 토큰을 생성한다.
        String token = jwtConfig.createToken(user.id);

        return new LoginResponse(token);

    }


    public void signUp(UserRequest userRequest){
        // 1. 이미 회원가입 했는지 체크
        if( userRepository.existsByEmail(userRequest.email) ){
            throw new RuntimeException();
        }
        // 2. 회원이 아니면, 회원가입 한다.
        // 2-1. 비번 암호화
        userRequest.password = passwordEncoder.encode(userRequest.password);

        // 2-2. DTO 를 Entity 로 변환
        User user = new User();
        user.email = userRequest.email;
        user.password = userRequest.password;
        user.nickname = userRequest.nickname;
        // 2-3. 디비에 저장.
        userRepository.save(user);
    }
}
