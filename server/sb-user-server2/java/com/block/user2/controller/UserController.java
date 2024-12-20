package com.block.user2.controller;

import com.block.user2.User2Application;
import com.block.user2.dao.UserDAO;
import com.block.user2.dto.UserAllResDTO;
import com.block.user2.dto.UserOneResDTO;
import com.block.user2.dto.UserResDTO;
import com.block.user2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserDAO userDAO;

    @PostMapping("/api/users")
    public ResponseEntity<UserResDTO> createUser(@RequestBody User user) {
        // 디비에 저장해야 한다. name과 email을 디비에 저장.

        try{

            int result = userDAO.createUser(user);
            if (result > 0) {
                // 성공에 대한 응답
                return ResponseEntity.status(200).body(new UserResDTO("success"));
            } else {
                // 실패에 대한 응답
                return ResponseEntity.status(500).body(new UserResDTO("fail"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }


    }

    @GetMapping("/api/users/{id}")
    ResponseEntity<UserOneResDTO> getUserById(@PathVariable long id){
        // DB에서 해당 아이디에 해당하는 유저 정보 받아온다.
        // 유저가 있는경우
        try{
            User user = userDAO.getUserById(id);
            return ResponseEntity.status(200).body(new UserOneResDTO("success", user));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }

    }

    @GetMapping("/api/users")
    public ResponseEntity<UserAllResDTO> getAllUsers(){

        // db에 저장된 유저들을 가져온다.
        List<User> userList = userDAO.getAllUsers();

        return ResponseEntity.status(200).body(new UserAllResDTO("success", userList.size(), userList ));

    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserResDTO> updateUser(@PathVariable long id, @RequestBody User user){

        // Db에 업데이트.
        try {
            int result = userDAO.updateUser(id, user);
            System.out.println("---- result : " + result);
            if(result > 0){
                return ResponseEntity.status(200).body(new UserResDTO("success"));
            }else{
                return ResponseEntity.status(200).body(new UserResDTO("no update"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(400).body(new UserResDTO("fail"));
        }

    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<UserResDTO> deleteUser(@PathVariable long id){
        // 디비에서 삭제

        try {
            int result = userDAO.deleteUser(id);
            if (result > 0){
                return ResponseEntity.status(200).body(new UserResDTO("success"));
            }else{
                return ResponseEntity.status(200).body(new UserResDTO("no delete"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new UserResDTO("fail"));
        }


    }



}






