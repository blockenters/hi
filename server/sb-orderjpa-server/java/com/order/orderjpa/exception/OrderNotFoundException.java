package com.order.orderjpa.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(long id) {
        super("오더 아이디 : " + id + "는 존재하지 않습니다.");
    }
}
