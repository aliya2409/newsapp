package com.javalab.newsportal.service.comments;

import com.javalab.newsportal.dao.CommentDAO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import com.javalab.newsportal.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service("commentsRetrievalService")
public class CommentsRetrievalServiceImpl implements CommentsRetrievalService {

    private CommentDAO commentDAO;
    private final String PROHIBITED_WORD = "luxoft";
    private static final Logger LOGGER = Logger.getLogger(CommentsRetrievalServiceImpl.class);

    public CommentsRetrievalServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public List<Comment> retrieve(News news, String sortBy) {
        Comparator<Comment> comparator;
        if (Constants.RATING_SORTING_OPTION.equals(sortBy)) {
            comparator = Comparator.comparing(Comment::getRating, Comparator.reverseOrder());
        } else {
           comparator = Comparator.comparing(Comment::getCreationDate, Comparator.reverseOrder());
        }
        return commentDAO.findByNews(news).stream()
                .filter(andLogFilteredOutValues(p -> !p.getContent().toLowerCase().contains(PROHIBITED_WORD)))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private static <T> Predicate<T> andLogFilteredOutValues(Predicate<T> predicate) {
        return value -> {
            if (predicate.test(value)) {
                return true;
            } else {
                LOGGER.info("Filtered: " + value);
                return false;
            }
        };
    }
}
