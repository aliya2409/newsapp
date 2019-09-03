package com.javalab.newsportal.service.news;

import com.javalab.newsportal.model.News;

import java.util.List;

public interface TodayNewsRetrievalService {

    List<News> retrieveTodayNews();
}
