package com.javalab.newsportal.model;

import java.util.Set;

public class News extends Publication {

    public static final String TITLE = "title";
    public static final String BRIEF = "brief";
    public static final String COMMENTS = "comments";

    private String title;
    private String brief;
    private Set<Comment> comments;

    public News() {
        super();
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", id='" + getId() + '\'' +
                ", content='" + getContent() + '\'' +
                ", creationDate='" + getCreationDate() + '\'' +
                '}';
    }
}
