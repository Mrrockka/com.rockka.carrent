package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GenericDaoImp<T> implements GenericDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImp (Class<T> type) { this.type = type;}

    protected Session getSession() {return sessionFactory.getCurrentSession();}

    private Class<T> type;

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
