package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Car;

import java.util.List;

public interface CarService {
    public List<Car> getAll();
    public Car getById(long id);
    public Car save(Car car);
    public Car delete(Car car);

}
