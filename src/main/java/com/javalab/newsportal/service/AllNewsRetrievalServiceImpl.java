package com.javalab.newsportal.service;

import com.javalab.newsportal.dao.NewsDAO;
import com.javalab.newsportal.model.News;

import java.util.List;

public class AllNewsRetrievalServiceImpl implements AllNewsRetrievalService {

    private final NewsDAO newsDAO;

    public AllNewsRetrievalServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public List<News> retrieveAll() {
        return newsDAO.findAll();
    }
}
