package com.block.jwt2.service;

import com.block.jwt2.config.JwtConfig;
import com.block.jwt2.dao.UserDAO;
import com.block.jwt2.dto.UserRequest;
import com.block.jwt2.entity.User;
import com.block.jwt2.util.EmailValidator;
import io.jsonwebtoken.Claims;
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

    public int signUp(UserRequest userRequest) {

        System.out.println("userRequest.email : " + userRequest.email);

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

    public Object userLogin(UserRequest userRequest){

        // 이메일 형식 체크
        if (EmailValidator.isValidEmail(userRequest.email) == false) {
            return 1;
        }

        // DB로 부터 유저 정보를 받아온다.
        try{
            User user = userDAO.userLogin(userRequest);
            System.out.println("userId : " + user.id);
            // 비밀번호가 맞는지 확인한다.
            if( passwordEncoder.matches( userRequest.password ,user.password) == false){
                // 비밀번호가 틀린경우.
                System.out.println(userRequest.password);
                System.out.println(user.password);
                return 3;
            }

            System.out.println("ok : "+userRequest.password);
            System.out.println("ok : "+user.password);

            // 인증토큰을 발급한다. JWT 토큰을 발급한다.
            // 가장 중요한 데이터는 유저 아이디다.
            // 유저 아이디를 토대로 JWT 토큰을 발급한다.
            String token = jwtConfig.createToken(user.id);
            System.out.println("token : " + token);

            Claims claims = jwtConfig.getTokenClaims(token);
            System.out.println("userId : " + claims.getSubject());

            return token;

        } catch (Exception e) {
            // 이메일이 없는 경우
            return 2;
        }

    }
}
