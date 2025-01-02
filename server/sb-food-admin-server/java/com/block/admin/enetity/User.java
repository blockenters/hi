package com.block.admin.enetity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public Long id;
    public String email;
    public String password;
    public String nickname;
    public String role;
    public String createdAt;
}


