package com.javalab.newsportal.model;

import java.time.LocalDateTime;

public abstract class Publication {

    public static final String ID = "id";
    public static final String CONTENT = "content";
    public static final String CREATION_DATE = "creationDate";

    private Long id;
    private LocalDateTime creationDate;
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", content='" + content + '\'' +
                '}';
    }
}
