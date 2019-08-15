package com.javalab.newsportal.controller;

import com.javalab.newsportal.model.News;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Executer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        News newsInstance = (News)context.getBean("news");
        System.out.printf(newsInstance.getContent());
    }
}
