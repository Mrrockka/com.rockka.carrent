package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 ** Class for database selections with car
 */
@Transactional
@Repository("carDao")
public class CarDaoImp extends GenericDaoImp<Car> implements CarDao {
    private final Logger logger = LoggerFactory.getLogger(CarDaoImp.class);

    public CarDaoImp(){super(Car.class);}
    /*
     ** Selecting car entity by id
     */
    @Override
    public Car getById(final long car_id) {
        Criteria criteria = getSession().createCriteria(Car.class);
        criteria.add(Restrictions.eq("id", car_id));
        return (Car) criteria.uniqueResult();
    }

}
