package com.javalab.newsportal.controller;

import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.comments.CommentRemovalService;
import com.javalab.newsportal.service.comments.CommentSavingService;
import com.javalab.newsportal.service.comments.CommentsRetrievalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showComments(HttpServletRequest request, Model model) {
        List<Comment> comments = commentsRetrievalService.retrieve((News) request.getAttribute("news"));
        model.addAttribute("news", request.getAttribute("news"));
        model.addAttribute("comment", request.getAttribute("comment"));
        model.addAttribute("comments", comments);
        return "viewNews";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("comment") Comment comment, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        commentSavingService.save(comment);
        return "redirect:/news/" + comment.getNews().getId();
    }

    @GetMapping("/delete/{newsId}/{commentId}")
    public String delete(@PathVariable(value = "newsId") Long newsId, @PathVariable(value = "commentId") Long commentId) {
        commentRemovalService.remove(commentId);
        return "redirect:/news/" + newsId;
    }
}
