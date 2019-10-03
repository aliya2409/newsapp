package com.javalab.newsportal.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Publication {

    public static final String ID = "id";
    public static final String CONTENT = "content";
    public static final String CREATION_DATE = "creationDate";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publication_seq")
    @SequenceGenerator(name = "publication_seq", sequenceName = "publication_seq", allocationSize = 1)
    private Long id;
    @Column(name = "cr_date", nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
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
