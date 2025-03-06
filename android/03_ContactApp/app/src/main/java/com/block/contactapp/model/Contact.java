package com.block.contactapp.model;

import java.io.Serializable;

public class Contact implements Serializable {
    public String name;
    public String phone;

    public Contact() {
    }

    public Contact(String email, String phone) {
        this.name = email;
        this.phone = phone;
    }
}
