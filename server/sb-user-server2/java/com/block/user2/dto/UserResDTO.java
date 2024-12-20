package com.block.user2.dto;

// DTO 클래스는, 요청이나 응답에 사용되는 클래스다
// 즉, JSON 을 저장하는 클래스다.
public class UserResDTO {
    public String status;

    public UserResDTO() {
    }

    public UserResDTO(String status) {
        this.status = status;
    }
}
