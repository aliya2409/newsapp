package com.javalab.newsportal.service.news;

import com.javalab.newsportal.dao.NewsDAO;
import com.javalab.newsportal.dao.exception.NoSuchNewsFoundException;
import com.javalab.newsportal.model.News;
import org.springframework.stereotype.Service;

@Service("newsRetrievalService")
public class NewsRetrievalServiceImpl implements NewsRetrievalService {

    private final NewsDAO newsDAO;

    public NewsRetrievalServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public News retrieve(Long id) {
        return newsDAO.findById(id).orElseThrow(
                () -> new NoSuchNewsFoundException("Could not find news with id: " + id));
    }
}
