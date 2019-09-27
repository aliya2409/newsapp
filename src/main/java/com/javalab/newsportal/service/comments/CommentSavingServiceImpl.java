package com.javalab.newsportal.service.comments;

import com.javalab.newsportal.dao.CommentDAO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.service.news.NewsRetrievalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("commentSavingService")
public class CommentSavingServiceImpl implements CommentSavingService {
    private CommentDAO commentDAO;
    private NewsRetrievalService newsRetrievalService;

    public CommentSavingServiceImpl(CommentDAO commentDAO,NewsRetrievalService newsRetrievalService) {
        this.commentDAO = commentDAO;
        this.newsRetrievalService = newsRetrievalService;
    }

    @Override
    public Comment save(Comment comment, Long newsId) {
        if (comment.getCreationDate() == null) {
            comment.setCreationDate(LocalDateTime.now());
        }
        if(comment.getRating() == null) {
            comment.setRating(0);
        }
        comment.setNews(newsRetrievalService.retrieve(newsId));
        return commentDAO.save(comment);
    }
}
