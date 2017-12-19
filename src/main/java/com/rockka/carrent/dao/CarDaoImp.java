package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CarDaoImp implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try {
            cars = sessionFactory.getCurrentSession().
                    createQuery("from Car").
                    list();
        }catch(ExceptionInInitializerError e){
            System.err.println("Exception with initializing bean " + e);
        }
        return cars;
    }

    @Override
    public Car getById(final long id) {
        Car car = null;
        try {
            car = (Car) sessionFactory.getCurrentSession().
                    createQuery("from Car where id = :id").
                    setParameter("id", id)
                    .uniqueResult();
        }catch(ExceptionInInitializerError e){
            System.err.println("Exception with initializing bean " + e);
        }
        return car;
    }

    @Override
    public void save(final Car car) {

    }

    @Override
    public void delete(final Car car) {

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
