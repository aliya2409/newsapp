package com.javalab.newsportal.controller;

import com.javalab.newsportal.dto.NewsDTO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.comments.CommentRemovalService;
import com.javalab.newsportal.service.comments.CommentSavingService;
import com.javalab.newsportal.service.comments.CommentsRetrievalService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("comments")
public class CommentsController {

    private CommentSavingService commentSavingService;
    private CommentsRetrievalService commentsRetrievalService;
    private CommentRemovalService commentRemovalService;

    public CommentsController(CommentSavingService commentSavingService, CommentsRetrievalService commentsRetrievalService,
                              CommentRemovalService commentRemovalService) {
        this.commentSavingService = commentSavingService;
        this.commentsRetrievalService = commentsRetrievalService;
        this.commentRemovalService = commentRemovalService;
    }

    @GetMapping
    public ResponseEntity<NewsDTO> showComments(@RequestParam(value = "sortBy", defaultValue = "date") String sortBy, HttpServletRequest request) {
        News news = (News) request.getAttribute("news");
        List<Comment> comments = commentsRetrievalService.retrieve(news, sortBy);
        news.setComments(comments);
        NewsDTO newsDTO = new NewsDTO(news);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity(newsDTO, headers, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Comment comment, @RequestHeader("newsId") Long newsId) {
        commentSavingService.save(comment, newsId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/delete/{commentId}")
    public ResponseEntity delete(@PathVariable(value = "commentId") Long commentId) {
        commentRemovalService.remove(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
