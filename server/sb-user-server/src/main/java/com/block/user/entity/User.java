package com.block.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 테이블에 저장된 데이터를 처리하기 위해서는
// 자바에서는 클래스 이므로
// 클래스명을 테이블명과 동일하게 만든다.
// 멤버변수 이름도 컬럼이름과 동일하게 만든다.

public class User {

    private Long id;
    private String name;
    private String email;
    private String createdAt;

    public User() {
    }

    public User(Long id, String name, String email, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
