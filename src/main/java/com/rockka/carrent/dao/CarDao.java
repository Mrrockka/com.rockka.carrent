package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Car;
/*
** Interface for database car selections
*/
public interface CarDao extends GenericDao<Car>{
    /*
    ** Selecting car entity by id
    */
    public Car getById(long car_id);
}
