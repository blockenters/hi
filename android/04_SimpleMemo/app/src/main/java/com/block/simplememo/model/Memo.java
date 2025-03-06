package com.block.simplememo.model;

import java.io.Serializable;

public class Memo implements Serializable {
     public int userId;
     public int id;
     public String title;
     public String body;

    public Memo() {
    }

    public Memo(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
