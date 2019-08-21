package com.javalab.newsportal.model;

import java.time.LocalDate;

public abstract class Publication {

    private Long id;
    private LocalDate creationDate;
    private String content;


    public Long getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
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
