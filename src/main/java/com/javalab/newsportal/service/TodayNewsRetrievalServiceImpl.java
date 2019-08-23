package com.javalab.newsportal.service;

import com.javalab.newsportal.dao.NewsDAO;
import com.javalab.newsportal.model.News;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TodayNewsRetrievalServiceImpl implements  TodayNewsRetrievalService{

    private static final String ORACLE_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss"; // where do i put constants? separate class?
    private NewsDAO newsDAO;

    public TodayNewsRetrievalServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public List<News> retrieveTodayNews() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ORACLE_TIMESTAMP_PATTERN);
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        String formattedDate = today.format(formatter);
        return newsDAO.findByDate(formattedDate);
    }
}
