package com.block.user.controller;

import com.block.user.dao.UserDAO;
import com.block.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserDAO userDAO;

    @PostMapping("/api/users")
    public Map<String, Object> createUser(@RequestBody User user){

        // user 클래스에 들어있는 데이터를
        // DB에 저장해야 한다.
        int result = userDAO.createUser(user);

        System.out.println("DB 저장 결과 : " + result);

        if(result > 0){
            return Map.of("status", "success");
        }else {
            return Map.of("status", "fail");
        }
    }

}
