package com.block.memoapp.model;

import java.io.Serializable;

public class Memo implements Serializable {

    public Long id;
    public Long userId;
    public String title;
    public String content;
    public String memoDate;
    public String createdAt;

    public Memo() {
    }

    public Memo(String title, String content, String memoDate) {
        this.title = title;
        this.content = content;
        this.memoDate = memoDate;
    }
}
