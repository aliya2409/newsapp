package com.javalab.newsportal.service;

import com.javalab.newsportal.dao.NewsDAO;
import org.springframework.stereotype.Service;

@Service
public class NewsRemovalServiceImpl implements NewsRemovalService {

    private final NewsDAO newsDAO;

    public NewsRemovalServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public void remove(Long id) {
        newsDAO.deleteByIdCriteria(id);
    }
}
