package com.javalab.newsportal.service;

import com.javalab.newsportal.dao.NewsDAO;
import com.javalab.newsportal.model.News;

import java.time.LocalDateTime;

public class NewsSavingServiceImpl implements NewsSavingService {

    private final NewsDAO newsDAO;

    public NewsSavingServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public News save(News news) {
        if (news.getCreationDate() == null) news.setCreationDate(LocalDateTime.now());
        return newsDAO.save(news);
    }
}
