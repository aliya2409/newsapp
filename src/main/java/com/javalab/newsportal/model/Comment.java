package com.javalab.newsportal.model;


public class Comment extends BaseEntity {

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
