package com.javalab.newsportal.controller;

import com.javalab.newsportal.dto.IdsDTO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.news.AllNewsRetrievalService;
import com.javalab.newsportal.service.news.NewsRemovalService;
import com.javalab.newsportal.service.news.NewsRetrievalService;
import com.javalab.newsportal.service.news.NewsSavingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("news")
public class NewsController {
    private final AllNewsRetrievalService allNewsRetrievalService;
    private final NewsRetrievalService newsRetrievalService;
    private final NewsSavingService newsSavingService;
    private final NewsRemovalService newsRemovalService;

    public NewsController(AllNewsRetrievalService allNewsRetrievalService,
                          NewsRetrievalService newsRetrievalService, NewsSavingService newsSavingService, NewsRemovalService newsRemovalService) {
        this.allNewsRetrievalService = allNewsRetrievalService;
        this.newsRetrievalService = newsRetrievalService;
        this.newsSavingService = newsSavingService;
        this.newsRemovalService = newsRemovalService;
    }

    @GetMapping("/allnews")
    public String listAllNews(Model model) {
        List<News> newsList = allNewsRetrievalService.retrieveAll();
        model.addAttribute("newsList", newsList);
        model.addAttribute("idsDTO", new IdsDTO(new ArrayList<>()));
        return "allnews";
    }

    @GetMapping("/{id}")
    public String resolveById(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        News news = newsRetrievalService.retrieve(id);
        request.setAttribute("news", news);
        request.setAttribute("comment", new Comment());
        return "forward:/comments";
    }

    @GetMapping("/showForm")
    public String getEditForm(@RequestParam(value = "newsId", required = false) Long newsId, Model model) {
        News news;
        if (newsId != null) {
            news = newsRetrievalService.retrieve(newsId);
        } else {
            news = new News();
        }
        model.addAttribute("news", news);
        return "editNews";
    }

    @PostMapping("/saveNews")
    public String submit(@ModelAttribute("news") News news,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        newsSavingService.save(news);
        return "redirect:/news/allnews";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        newsRemovalService.remove(id);
        return "redirect:/news/allnews";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("listDTO") IdsDTO idsDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        newsRemovalService.removeList(idsDTO.getIds());
        return "redirect:/news/allnews";
    }
}
