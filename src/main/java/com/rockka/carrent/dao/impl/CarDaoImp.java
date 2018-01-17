package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Repository("carDao")
public class CarDaoImp extends GenericDaoImp<Car> implements CarDao {
    private final Logger logger = LoggerFactory.getLogger(CarDaoImp.class);

    public CarDaoImp(){super(Car.class);}
    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try {
            cars = getSession()
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
            car = (Car) getSession()
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
            getSession()
                    .save(car.setModifiedAt(new Date()));
        }catch(Exception ex){
            logger.error("" + ex);
        }
        return car;
    }

    @Override
    public Car delete(Car car) {
        update(car.setDeleted());
        return car;
    }

    @Override
    public Car update(Car car){
        try {
            car.setModifiedAt(new Date());
            getSession().update(car);
        }catch(Exception ex){
            logger.error("" + ex);
        }
        return car;
    }
}
