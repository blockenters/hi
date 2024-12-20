package com.block.user2.dto;

import com.block.user2.entity.User;

public class UserOneResDTO {
    public String status;
    public User data;

    public UserOneResDTO() {
    }

    public UserOneResDTO(String status, User data) {
        this.status = status;
        this.data = data;
    }
}
