package com.javalab.newsportal.service;

import com.javalab.newsportal.model.Comment;

public interface CommentSavingService {

    Comment save(Comment comment);
}
