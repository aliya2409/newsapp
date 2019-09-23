package com.javalab.newsportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NEWS")
@NamedQuery(name = "News.findByTitle", query = "SELECT n FROM News n WHERE lower(n.title) LIKE :title")
@NamedNativeQuery(name = "News.findByDate", resultClass = News.class, query="SELECT n.ID, n.TITLE, n.BRIEF, p.CONTENT, p.CR_DATE FROM News n JOIN Publication p ON p.id = n.id WHERE p.CR_DATE = to_date(:creationDate, 'YYYY-MM-DD hh24:mi:ss')")
public class News extends Publication {

    public static final String TITLE = "title";
    public static final String BRIEF = "brief";
    public static final String COMMENTS = "comments";

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String brief;
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;

    public News() {
        super();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
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
