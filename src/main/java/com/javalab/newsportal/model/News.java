package com.javalab.newsportal.model;

import java.time.LocalDate;

public class News extends BaseEntity {

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
}
