package com.javalab.newsportal.dao;

import com.javalab.newsportal.model.News;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
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

    @Transactional
    public List<News> findByDate(String date) {
        Query<News> query = getCurrentSession().createNamedQuery("News.findByDate", clazz);
        query.setParameter("creationDate", date);
        return query.getResultList();
    }

    public News findByIdCriteria(Long id) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<News> query = cb.createQuery(clazz);
        Root<News> root = query.from(clazz);
        query.select(root).where(cb.equal(root.get(News.ID), id));
        return getCurrentSession().createQuery(query).getSingleResult();
    }

    @Transactional
    public List<News> findAllCriteria() {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<News> query = cb.createQuery(clazz);
        Root<News> root = query.from(clazz);
        query.select(root);
        return getCurrentSession().createQuery(query).getResultList();
    }

    public News updateCriteria(News news) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaUpdate<News> update = cb.createCriteriaUpdate(clazz);
        Root<News> root = update.from(clazz);
        update.set("title", news.getTitle());
        update.set("brief", news.getBrief());
        update.set("content", news.getContent());
        update.set("creationDate", news.getCreationDate());
        update.where(cb.equal(root.get("id"), news.getId()));
        getCurrentSession().createQuery(update).executeUpdate();
        return news;
    }

    public void deleteByIdCriteria(News news) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaDelete<News> delete = cb.createCriteriaDelete(clazz);
        Root root = delete.from(clazz);
        delete.where(cb.equal(root.get("id"), news.getId()));
        getCurrentSession().createQuery(delete).executeUpdate();
    }

}
