package com.javalab.newsportal.controller;

import com.javalab.newsportal.dto.IdsDTO;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.news.AllNewsRetrievalService;
import com.javalab.newsportal.service.news.NewsRemovalService;
import com.javalab.newsportal.service.news.NewsRetrievalService;
import com.javalab.newsportal.service.news.NewsSavingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
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
        return "forward:/comments";
    }

    @PostMapping("/saveNews")
    public ResponseEntity submit(@RequestBody News news,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        newsSavingService.save(news);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        newsRemovalService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@ModelAttribute("listDTO") IdsDTO idsDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        newsRemovalService.removeList(idsDTO.getIds());
        return new ResponseEntity(HttpStatus.OK);
    }
}
