package com.block.admin.service;

import com.block.admin.dao.UserDAO;
import com.block.admin.dto.UserRequest;
import com.block.admin.util.EmailValidator;
import com.block.admin.util.NicknameValidator;
import com.block.admin.util.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDAO userDAO;

    public int signUp(UserRequest userRequest){
        // 1. 이메일이 유효한 형식인지 확인.
        if ( EmailValidator.isValidEmail(userRequest.email) == false){
            return 1;
        }
        // 2. foodreview.com 으로 되어있는지 확인.
        if( userRequest.email.contains("foodreview.com") == false){
            return 2;
        }
        // 3. 비밀번호는 최소 10자 이상, 영문 대/소문자/숫자/특수문자 모두 포함 필수
        if( PasswordValidator.isValid(userRequest.password) == false){
            return 3;
        }
        // 4. 닉네임은 2-20자 이내, 한글/영문/숫자 허용
        if( NicknameValidator.isValid(userRequest.nickname) == false){
            return 4;
        }

        // 5. 비밀번호를 암호화 한다.
        userRequest.password = passwordEncoder.encode(userRequest.password);

        // 6. DAO 한테 넘겨준다.
        userDAO.signUp(userRequest);

        return 0;

    }

}
