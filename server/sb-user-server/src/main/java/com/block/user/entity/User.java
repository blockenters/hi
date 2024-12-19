package com.block.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 테이블에 저장된 데이터를 처리하기 위해서는
// 자바에서는 클래스 이므로
// 클래스명을 테이블명과 동일하게 만든다.
// 멤버변수 이름도 컬럼이름과 동일하게 만든다.
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String createdAt;

}
