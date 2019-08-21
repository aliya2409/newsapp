package com.javalab.newsportal.model;


public class Comment extends Publication {

    private String author;
    private Long newsId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", newsId=" + newsId +
                '}';
    }
}
