package com.javalab.newsportal.service.comments;

import com.javalab.newsportal.dao.CommentDAO;
import com.javalab.newsportal.model.Comment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("commentSavingService")
public class CommentSavingServiceImpl implements CommentSavingService {
    private CommentDAO commentDAO;

    public CommentSavingServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getCreationDate() == null) {
            comment.setCreationDate(LocalDateTime.now());
        }
        if(comment.getRating() == null) {
            comment.setRating(0);
        }
        return commentDAO.save(comment);
    }
}
