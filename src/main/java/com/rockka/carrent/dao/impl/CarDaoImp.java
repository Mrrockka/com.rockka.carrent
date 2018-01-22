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

//    TODO rewrite with criteria
    @Override
    public Car getById(final long id) {
        Car car = null;
        try {
            car = (Car) getSession()
                    .createQuery("from Car where id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
        }catch(Exception e){
            logger.error("Exception " + e);
        }
        return car;
    }

}
