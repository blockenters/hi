package com.block.travel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Email
    public String email;
    @Size(min = 4, max = 20)
    public String password;
    @Size(min = 2)
    public String nickname;

}
