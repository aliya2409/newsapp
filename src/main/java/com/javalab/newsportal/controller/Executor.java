package com.javalab.newsportal.controller;

import com.javalab.newsportal.dao.NewsDAO;
import com.javalab.newsportal.model.News;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Executor {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
//        News newsInstance = context.getBean("news", News.class);
//        newsInstance.setContent("bust it");
//        newsInstance.setTitle("gggg");
//        newsInstance.setBrief("ggggg");
//        newsInstance.setCreationDate(LocalDate.now());
//        NewsDAO newsDAO = context.getBean("newsDAO", NewsDAO.class);
//        List<News> news = newsDAO.findByTitle("kitties");
//        System.out.println(news.size());
//        for(News newsInc : news) {
//            System.out.println(newsInc);
//        }
//            newsInstance = newsDAO.create(newsInstance);
//        News newsInstance = newsDAO.findById(22);
//        System.out.println(newsInstance);
//        AllNewsRetrievalServiceImpl service = context.getBean("allNewsRetrievalService", AllNewsRetrievalServiceImpl.class);
//        service.newsDAO = newsDAO;
//        List<News> news = service.retrieveAll();
//        for(News newsInc : news) {
//            System.out.println(newsInc);
//        }
    }
}
