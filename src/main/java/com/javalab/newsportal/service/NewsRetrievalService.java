package com.javalab.newsportal.service;

import com.javalab.newsportal.model.News;

public interface NewsRetrievalService {

    News retrieve(Long id);
}
