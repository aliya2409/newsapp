package com.javalab.newsportal.controller;

import com.javalab.newsportal.dto.NewsDTO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.comments.CommentRemovalService;
import com.javalab.newsportal.service.comments.CommentSavingService;
import com.javalab.newsportal.service.comments.CommentsRetrievalService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
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
        return ResponseEntity.ok(newsDTO);
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute("comment") Comment comment, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        commentSavingService.save(comment);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/news/" + comment.getNews().getId());
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }

    @GetMapping("/delete/{newsId}/{commentId}")
    public ResponseEntity delete(@PathVariable(value = "newsId") Long newsId, @PathVariable(value = "commentId") Long commentId) {
        commentRemovalService.remove(commentId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/news/" + newsId);
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }
}
