package com.javalab.newsportal.controller;

import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.AllNewsRetrievalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HelloWorldController {
    AllNewsRetrievalService allNewsRetrievalService;

    public HelloWorldController(AllNewsRetrievalService allNewsRetrievalService) {
        this.allNewsRetrievalService = allNewsRetrievalService;
    }

    @RequestMapping("/welcome")
    public String helloWorld(Model model) {
        String message = "Hello World, Spring MVC Tutorial. This message is coming from HelloWorldController.java";
        model.addAttribute("message", message);
        return "welcome";
    }

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @RequestMapping("/allnews")
    public String listAllNews(Model model) {
        List<News> newsList = allNewsRetrievalService.retrieveAll();
        model.addAttribute("newsList", newsList);
        return "allnews";
    }
}
