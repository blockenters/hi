package com.block.contactapp.model;

public class Contact {
    public String name;
    public String phone;

    public Contact() {
    }

    public Contact(String email, String phone) {
        this.name = email;
        this.phone = phone;
    }
}
