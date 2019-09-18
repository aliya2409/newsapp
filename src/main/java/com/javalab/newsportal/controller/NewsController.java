package com.javalab.newsportal.controller;

import com.javalab.newsportal.dto.IdsDTO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.news.AllNewsRetrievalService;
import com.javalab.newsportal.service.news.NewsRemovalService;
import com.javalab.newsportal.service.news.NewsRetrievalService;
import com.javalab.newsportal.service.news.NewsSavingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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

    @GetMapping(value = "/allnews", headers = "Accept=application/json")
    public ResponseEntity<List<News>> listAllNews(HttpServletRequest request) {
        List<News> newsList = allNewsRetrievalService.retrieveAll();
        request.setAttribute("idsDTO", new IdsDTO(new ArrayList<>()));
        return ResponseEntity.ok(newsList);
    }

    @GetMapping(value = "/{id}")
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
    public ResponseEntity submit(@ModelAttribute("news") News news,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        newsSavingService.save(news);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/news/allnews");
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }

    @PostMapping("/date")
    public ResponseEntity date(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        System.out.println(date);
        return ResponseEntity.ok(date);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        newsRemovalService.remove(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/news/allnews");
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@ModelAttribute("listDTO") IdsDTO idsDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        newsRemovalService.removeList(idsDTO.getIds());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/news/allnews");
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }
}
