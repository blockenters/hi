package com.block.posting.service;

import com.block.posting.config.JwtConfig;
import com.block.posting.dao.UserDAO;
import com.block.posting.dto.LoginRes;
import com.block.posting.dto.SignUpReq;
import com.block.posting.entity.User;
import com.block.posting.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void signUp(SignUpReq signUpReq){
        if(!EmailValidator.isValidEmail(signUpReq.email)){
            throw new RuntimeException();
        }

        signUpReq.password = passwordEncoder.encode(signUpReq.password);

        // DB에 저장하는 로직
        userDAO.signUp(signUpReq);

    }

    public LoginRes login(SignUpReq signUpReq) {
        if(!EmailValidator.isValidEmail(signUpReq.email)){
            throw new RuntimeException();
        }

        User user = userDAO.login(signUpReq);

        System.out.println("user : " + user.id + " " + user.email + " " + user.password);
        System.out.println("signUpReq : " + signUpReq.email + " " + signUpReq.password);
        if( !passwordEncoder.matches(signUpReq.password, user.password)){
            throw new RuntimeException();
        }

        String token = jwtConfig.createToken(user.id);

        LoginRes loginRes = new LoginRes(token);
        return loginRes;
    }
}
