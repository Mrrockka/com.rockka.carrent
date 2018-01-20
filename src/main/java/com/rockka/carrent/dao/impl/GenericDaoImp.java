package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class GenericDaoImp<T> implements GenericDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> type;
    private final Logger logger = LoggerFactory.getLogger(GenericDaoImp.class);

    public GenericDaoImp (Class<T> type) { this.type = type;}

    protected Session getSession() {return sessionFactory.getCurrentSession();}

    @Override
    public T save(T o) {
        getSession().save(o);
        return o;
    }

    @Override
    public T update(T o) {
        getSession().update(o);
        return o;
    }

    @Override
    public T delete(T o) {
        getSession().delete(o);
        return o;
    }

    @Override
    public List<T> getAll() {
        Criteria criteria = getSession().createCriteria(type);
        return criteria.list();
    }

}
