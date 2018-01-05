package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Car;

import java.util.List;

public interface CarDao {
    public List<Car> getAll();
    public List<Car> getAllWithoutDetails();
    public Car getById(long id);
    public Car save(Car car);
    public Car delete(Car car);

}
