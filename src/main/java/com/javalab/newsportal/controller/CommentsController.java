package com.javalab.newsportal.controller;

import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.service.comments.CommentRemovalService;
import com.javalab.newsportal.service.comments.CommentSavingService;
import com.javalab.newsportal.service.comments.CommentsRetrievalService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    public String showComments(@RequestParam(value = "sortBy", defaultValue = "date") String sortBy, HttpServletRequest request, Model model) {
        List<Comment> comments = commentsRetrievalService.retrieve((News) request.getAttribute("news"), sortBy);
        model.addAttribute("news", request.getAttribute("news"));
        model.addAttribute("comment", request.getAttribute("comment"));
        model.addAttribute("comments", comments);
        return "viewNews";
    }

    @PostMapping("/save")
    @PreAuthorize("isAuthenticated()")
    public String save(@ModelAttribute("comment") Comment comment, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "index";
        }
        comment.setAuthor(principal.getName());
        commentSavingService.save(comment);
        return "redirect:/news/" + comment.getNews().getId();
    }

    @PostMapping("/delete/{newsId}/{commentId}")
    @PreAuthorize("hasAuthority(T(com.javalab.newsportal.model.UserRoles).MODERATOR.name()) or isAuthenticated() and #username == principal.username")
    public String delete(@PathVariable Long newsId, @PathVariable Long commentId, @RequestParam(value = "username") String username) {
        commentRemovalService.remove(commentId);
        return "redirect:/news/" + newsId;
    }
}
