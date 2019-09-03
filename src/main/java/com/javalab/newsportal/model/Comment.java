package com.javalab.newsportal.model;


public class Comment extends Publication {

    public static final String AUTHOR = "author";
    public static final String NEWS = "news";

    private String author;
    private News news;
    private Integer rating;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + getId() + '\'' +
                ", author='" + author + '\'' +
                ", content='" + getContent() + '\'' +
                ", newsId=" + news.getId() +
                '}';
    }
}
