package com.javalab.newsportal.model;

import java.time.LocalDateTime;

public abstract class Publication {

    private Long id;
    private LocalDateTime creationDate;
    private String content;


    public Long getId() {
        return id;
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
