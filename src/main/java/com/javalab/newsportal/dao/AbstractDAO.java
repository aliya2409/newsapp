package com.javalab.newsportal.dao;

import com.javalab.newsportal.model.Publication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> {
    protected Class<T> clazz;
    private SessionFactory sessionFactory;

    public AbstractDAO() {
    }

    public AbstractDAO(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Transactional
    public Optional<T> findById(long id) {
        return Optional.ofNullable(getCurrentSession().get(clazz, id));
    }

    @Transactional
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName() + " p order by p." + Publication.CREATION_DATE + " desc").list();
    }

    @Transactional
    public T save(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    @Transactional
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Transactional
    public void deleteById(long entityId) {
        Optional<T> entity = findById(entityId);
        entity.ifPresent(ent -> delete(entity.get()));
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
