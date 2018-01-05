package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Car;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository("carDao")
public class CarDaoImp implements CarDao{
    private final Logger logger = LoggerFactory.getLogger(CarDaoImp.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> getAllWithoutDetails() {
        List<Car> cars = new ArrayList<>();
        try {
            cars = sessionFactory.getCurrentSession().
                    createQuery("select C.name, C.price, C.createdAt, C.modifiedAt, C.isDeleted from Car as C where isDeleted = 'n'").
                    list();
        }catch(Exception e){
            logger.error("Exception " + e);
        }
        return cars;
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try {
            cars = sessionFactory.getCurrentSession().
                    createQuery("from Car").
                    list();
        }catch(Exception e){
            logger.error("Exception " + e);
        }
        return cars;
    }

    @Override
    public Car getById(final long id) {
        Car car = null;
        try {
            car = (Car) sessionFactory.getCurrentSession().
                    createQuery("from Car where id = :id and isDeleted = 'n'").
                    setParameter("id", id)
                    .uniqueResult();
        }catch(Exception e){
            logger.error("Exception " + e);
        }
        return car;
    }

    @Override
    public Car save(Car car) {
        if(car.getCreatedAt() == null){
            car.setCreatedAt(new Date());
        }
        sessionFactory.getCurrentSession().saveOrUpdate(car.setModifiedAt(new Date()));
        return car;
    }

    @Override
    public Car delete(Car car) {
        car.setDeleted();
        save(car);
        return car;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
