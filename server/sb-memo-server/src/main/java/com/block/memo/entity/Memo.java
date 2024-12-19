package com.block.memo.entity;

public class Memo {
    public long id;
    public String title;
    public String content;

    public Memo() {
    }

    public Memo(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
