package com.block.jwt.service;

import com.block.jwt.dto.UserRequest;
import com.block.jwt.util.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    signUp(UserRequest userRequest) {
        // 이메일 주소가 유효한지 확인
        if( EmailValidator.isValidEmail(userRequest.email) ==false ){
            return 1;
        }
        // 비밀번호는 최소 8자 이상이어야 합니다.
        if(userRequest.password.length() < 8){
            return 2;
        }
    }

}
