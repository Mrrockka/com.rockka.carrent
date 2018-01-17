package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Car;

public interface CarDao extends GenericDao<Car>{
    public Car getById(long id);
}
