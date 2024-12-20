package com.block.user2.dto;

import com.block.user2.entity.User;

import java.util.List;

public class UserAllResDTO {

    public String status;
    public Integer count;
    public List<User> data;

    public UserAllResDTO() {
    }

    public UserAllResDTO(String status, Integer count, List<User> data) {
        this.status = status;
        this.count = count;
        this.data = data;
    }
}
