package com.javalab.newsportal.service.comments;

import com.javalab.newsportal.model.Comment;

public interface CommentSavingService {

    Comment save(Comment comment, Long newsId);
}
