package com.block.sb_memo_server.service;

import com.block.sb_memo_server.config.JwtConfig;
import com.block.sb_memo_server.dao.UserDAO;
import com.block.sb_memo_server.dto.LoginRes;
import com.block.sb_memo_server.dto.SignUpRequest;
import com.block.sb_memo_server.entity.User;
import com.block.sb_memo_server.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDAO userDAO;

    @Autowired
    JwtConfig jwtConfig;

    public LoginRes login(SignUpRequest signUpRequest){

        if (EmailValidator.isValidEmail(signUpRequest.email) == false){
            throw new RuntimeException();
        }
        // Dao 이용해서 유저 정보 가져온다.
        User user = userDAO.login(signUpRequest);
        // 비번 맞는지 체크.
        if( !passwordEncoder.matches(signUpRequest.password, user.password) ){
            throw new RuntimeException();
        }

        String token = jwtConfig.createToken(user.id);
        return new LoginRes(token);
    }



    public void signUp(SignUpRequest signUpRequest){
        // 1. 이메일 형식이 올바르냐.
        if( EmailValidator.isValidEmail(signUpRequest.email) ==false ){
            throw new RuntimeException();
        }

        // 2. 비번 암호화
        signUpRequest.password = passwordEncoder.encode(signUpRequest.password);

        // 3. db에 저장,  중복 이메일인지도 확인 가능.
        try {
            userDAO.signUp(signUpRequest);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
