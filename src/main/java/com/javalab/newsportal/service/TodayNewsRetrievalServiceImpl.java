package com.javalab.newsportal.service;

import com.javalab.newsportal.dao.NewsDAO;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.util.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("todayNewsRetrievalService")
public class TodayNewsRetrievalServiceImpl implements  TodayNewsRetrievalService{

    private final NewsDAO newsDAO;

    public TodayNewsRetrievalServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public List<News> retrieveTodayNews() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.ORACLE_TIMESTAMP_PATTERN);
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        String formattedDate = today.format(formatter);
        return newsDAO.findByDate(formattedDate);
    }
}
