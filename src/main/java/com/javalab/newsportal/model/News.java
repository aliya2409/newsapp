package com.javalab.newsportal.model;

public class News extends Publication {

    private String title;
    private String brief;

    public News() {
        super();
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
                ", creationDate='" + getCreationDate() + '\''+
                '}';
    }
}
