package com.javalab.newsportal.service.news;

import com.javalab.newsportal.dao.NewsDAO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void removeList(List<Long> ids) {
        newsDAO.deletePickedNewsById(ids);
    }
}
