package com.javalab.newsportal.service;

import com.javalab.newsportal.dao.CommentDAO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentsRetrievalService")
public class CommentsRetrievalServiceImpl implements CommentsRetrievalService {

    private CommentDAO commentDAO;

    public CommentsRetrievalServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public List<Comment> retrieve(News news) {
        return commentDAO.findByNews(news);
    }
}
