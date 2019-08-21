package com.javalab.newsportal.service;

import com.javalab.newsportal.dao.NewsDAO;
import com.javalab.newsportal.model.News;

public class NewsAdditionNewsAdditionServiceImpl implements NewsAdditionService {

    private NewsDAO newsDAO;

    @Override
    public News add(News news) {
        News addedNews = newsDAO.create(news);
        return addedNews;
    }
}
