package com.javalab.newsportal.controller;

import com.javalab.newsportal.model.News;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Executor {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        News newsInstance = context.getBean("news", News.class);
        System.out.printf(newsInstance.getContent());
    }
}
