package com.javalab.newsportal.dao;

import com.javalab.newsportal.model.User;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class UserDAO extends AbstractDAO<User> {

    public UserDAO(SessionFactory sessionFactory, Class<User> clazz) {
        super(sessionFactory, clazz);
    }

    public Optional<User> findByUsername(String username) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(clazz);
        Root<User> root = query.from(clazz);
        query.select(root).where(cb.equal(root.get(User.USERNAME), username));
        return Optional.ofNullable(getCurrentSession().createQuery(query).getSingleResult());
    }
}
