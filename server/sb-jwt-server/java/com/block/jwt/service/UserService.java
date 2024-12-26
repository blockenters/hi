package com.block.jwt.service;

import com.block.jwt.dao.UserDAO;
import com.block.jwt.dto.UserRequest;
import com.block.jwt.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDAO userDAO;

    public int signUp(UserRequest userRequest) {
        // 이메일 주소가 유효한지 확인
        if( EmailValidator.isValidEmail(userRequest.email) ==false ){
            return 1;
        }
        // 비밀번호는 최소 8자 이상이어야 합니다.
        if(userRequest.password.length() < 8){
            return 2;
        }

        // 비밀번호 암호화 한다
        String password = passwordEncoder.encode(userRequest.password);

        System.out.println("userRequest.password : " + userRequest.password);
        System.out.println("password : " + password);

        // 암호화된 비밀번호를 다시 userRequest에 넣어준다.
        userRequest.password = password;

        // DB에 저장한다.
        try {
            userDAO.signUp(userRequest);
        } catch (Exception e) {
            // 동일한 이메일로 회원가입을 하려고 하면 DB 에러가 발생한다.
            return 3;
        }
        return 0;

    }

}
