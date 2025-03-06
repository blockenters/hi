package com.block.sb_memo_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    public Long id;
    public String email;
    public String password;
    public String nickname;

}
