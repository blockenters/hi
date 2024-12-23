package com.block.review.service;

import com.block.review.dao.UserDAO;
import com.block.review.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public int createUser(UserRequest userRequest){

        //   이메일은 유효한 이메일 형식이어야 합니다.
        if (userRequest.email.contains("@") == false ){
            return 1;
        }
        //   비밀번호는 최소 8자 이상이어야 합니다.
        if (userRequest.password.length() < 8){
            return 2;
        }
        //   닉네임은 2자 이상 30자 이하여야 합니다.
        if (userRequest.nickname.length() < 2 || userRequest.nickname.length() > 30){
            return 3;
        }
        try{
            userDAO.createUser(userRequest);
            return 0;
        } catch (Exception e) {
            // 중복이메일인경우. : DB 유니크 에러난 경우.
            return 4;
        }
    }



}
