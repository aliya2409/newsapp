package com.javalab.newsportal.dao;

import com.javalab.newsportal.model.News;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

public class NewsDAO extends AbstractDAO<News> {

    public NewsDAO(SessionFactory sessionFactory, Class<News> clazz) {
        super(sessionFactory, clazz);
    }

    @Transactional
    public List<News> findByTitle(String title) {
        Query<News> query = getCurrentSession().createNamedQuery("News.findByTitle", clazz);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

}
