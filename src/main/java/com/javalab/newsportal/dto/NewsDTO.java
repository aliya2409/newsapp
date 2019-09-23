package com.javalab.newsportal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;

import java.time.LocalDateTime;
import java.util.List;

public class NewsDTO {

    private String title;
    private String brief;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creationDate;
    private Long id;
    private List<Comment> comments;

    public NewsDTO(News news) {
        this.title = news.getTitle();
        this.brief = news.getBrief();
        this.id = news.getId();
        this.content = news.getContent();
        this.comments = news.getComments();
        this.creationDate = news.getCreationDate();
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
