package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Car;

import java.util.List;

public interface CarDao {
    public List<Car> getAll();
    public Car getById(long id);
    public void save(Car car);
    public void delete(Car car);

}
