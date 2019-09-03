package com.javalab.newsportal.service.comments;

import com.javalab.newsportal.dao.CommentDAO;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service("commentsRetrievalService")
public class CommentsRetrievalServiceImpl implements CommentsRetrievalService {

    private CommentDAO commentDAO;
    private final String PROHIBITED_WORD = "luxoft";

    public CommentsRetrievalServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public List<Comment> retrieve(News news) {
        Comparator<Comment> commentDateComparator = Comparator.comparing(Comment::getCreationDate, Comparator.reverseOrder());
        Comparator<Comment> commentRatingComparator = Comparator.comparing(Comment::getRating, Comparator.reverseOrder());
        return commentDAO.findByNews(news).stream()
                .filter(andLogFilteredOutValues(p -> !p.getContent().toLowerCase().contains(PROHIBITED_WORD)))
                .sorted(commentDateComparator)
                .collect(Collectors.toList());
    }

    private static <T> Predicate<T> andLogFilteredOutValues(Predicate<T> predicate) {
        return value -> {
            if (predicate.test(value)) {
                return true;
            } else {
                System.out.println("Filtered: " + value);
                return false;
            }
        };
    }
}
