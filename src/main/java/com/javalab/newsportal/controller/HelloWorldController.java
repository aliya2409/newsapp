package com.javalab.newsportal.controller;

import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.AllNewsRetrievalService;
import com.javalab.newsportal.service.NewsSavingService;
import com.javalab.newsportal.service.NewsRetrievalService;
import com.javalab.newsportal.service.TodayNewsRetrievalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("news")
public class HelloWorldController {
    private final AllNewsRetrievalService allNewsRetrievalService;
    private final TodayNewsRetrievalService todayNewsRetrievalService;
    private final NewsRetrievalService newsRetrievalService;
    private final NewsSavingService newsSavingService;

    public HelloWorldController(AllNewsRetrievalService allNewsRetrievalService, TodayNewsRetrievalService todayNewsRetrievalService,
                                NewsRetrievalService newsRetrievalService, NewsSavingService newsSavingService) {
        this.allNewsRetrievalService = allNewsRetrievalService;
        this.todayNewsRetrievalService = todayNewsRetrievalService;
        this.newsRetrievalService = newsRetrievalService;
        this.newsSavingService = newsSavingService;
    }

    @GetMapping("/allnews")
    public String listAllNews(Model model) {
        List<News> newsList = allNewsRetrievalService.retrieveAll();
        model.addAttribute("newsList", newsList);
        return "allnews";
    }

    @GetMapping("/{id}")
    public String resolveById(@PathVariable(value = "id") Long id, Model model) {
        News news = newsRetrievalService.retrieve(id);
        model.addAttribute("news", news);
        return "viewNews";
    }

    @GetMapping("/addNews")
    public String getEditForm(@RequestParam(value="newsId", required = false) Long newsId, Model model) {
        model.addAttribute("newsId", newsId);
        return "editNews";
    }

    @ModelAttribute("news")
    public News getNewsObject(@RequestParam(value="newsId", required = false) Long newsId) {
        return newsId != null ? newsRetrievalService.retrieve(newsId) : new News();
    }

    @PostMapping("/editNews")
    public String submit(@ModelAttribute("news") News news,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        newsSavingService.save(news);
        return "redirect:/news/allnews";
    }
}
