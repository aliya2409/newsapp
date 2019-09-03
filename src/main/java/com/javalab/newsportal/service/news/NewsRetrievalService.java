package com.javalab.newsportal.service.news;

import com.javalab.newsportal.model.News;

public interface NewsRetrievalService {

    News retrieve(Long id);
}
