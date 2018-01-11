package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.CarService;
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
public class CarDaoImp implements CarService {
    private final Logger logger = LoggerFactory.getLogger(CarDaoImp.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try {
            cars = sessionFactory.getCurrentSession()
                    .createQuery("from Car")
                    .list();
        }catch(Exception e){
            logger.error("Exception " + e);
        }
        return cars;
    }

    @Override
    public Car getById(final long id) {
        Car car = null;
        try {
            car = (Car) sessionFactory.getCurrentSession()
                    .createQuery("from Car where id = :id and isDeleted = 'n'")
                    .setParameter("id", id)
                    .uniqueResult();
        }catch(Exception e){
            logger.error("Exception " + e);
        }
        return car;
    }

    @Override
    public Car save(Car car) {
        try{
            if(car.getCreatedAt() == null){
                car.setCreatedAt(new Date()).setIsDeleted('n');
            }
            sessionFactory.getCurrentSession()
                    .saveOrUpdate(car.setModifiedAt(new Date()));
        }catch(Exception ex){
            car.setCreatedAt(null);
            logger.error("" + ex);
        }
        return car;
    }

    @Override
    public Car delete(Car car) {
        try{
            save(car.setDeleted());
        }catch(Exception ex){
            logger.error("" +ex);
        }
        return car;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
