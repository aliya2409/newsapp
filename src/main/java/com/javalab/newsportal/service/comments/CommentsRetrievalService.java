package com.javalab.newsportal.service.comments;

import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;

import java.util.List;

public interface CommentsRetrievalService {

    List<Comment> retrieve(News news, String sortBy);
}
