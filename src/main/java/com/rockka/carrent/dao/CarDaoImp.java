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
    @Transactional
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try {
            cars = sessionFactory.getCurrentSession().
                    createQuery("from Car where isDeleted = 'n'").
                    list();
        }catch(Exception e){
            logger.error("Exception " + e);
        }
        return cars;
    }

    @Override
    @Transactional
    public Car getById(final long id) {
        Car car = null;
        try {
            car = (Car) sessionFactory.getCurrentSession().
                    createQuery("from Car where id = :id").
                    setParameter("id", id)
                    .uniqueResult();
        }catch(Exception e){
            logger.error("Exception " + e);
        }
        return car;
    }

    @Override
    @Transactional
    public void save(final Car car) {
        sessionFactory.getCurrentSession().saveOrUpdate(car);
        logger.info("Car id " + car.getId());
    }

    @Override
    @Transactional
    public void delete(final Car car) {
        car.setDeleted().setModifiedAt(new Date());
        save(car);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
