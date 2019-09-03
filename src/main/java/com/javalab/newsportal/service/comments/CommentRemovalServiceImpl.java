package com.javalab.newsportal.service.comments;

import com.javalab.newsportal.dao.CommentDAO;
import org.springframework.stereotype.Service;

@Service
public class CommentRemovalServiceImpl implements CommentRemovalService {
    private final CommentDAO commentDAO;

    public CommentRemovalServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public void remove(Long id) {
        commentDAO.deleteById(id);
    }
}
