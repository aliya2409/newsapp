package com.javalab.newsportal.dao;

import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

public class CommentDAO extends AbstractDAO<Comment> {

    public CommentDAO(SessionFactory sessionFactory, Class<Comment> clazz) {
        super(sessionFactory, clazz);
    }

    @Transactional
    public List<Comment> findByNews(News news) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Comment> query = cb.createQuery(clazz);
        Root<Comment> root = query.from(clazz);
        query.select(root).where(cb.equal(root.get(Comment.NEWS), news));
        return getCurrentSession().createQuery(query).getResultList();
    }

}
