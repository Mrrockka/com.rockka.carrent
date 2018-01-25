package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.domain.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    public Order getById(long id);
    public List<Order> getAllWithUser(String username, boolean withDeleted);
    public List<Order> getAllWithCar(long carId, boolean withDeleted);
    public Order getByIdWithUser(long orderId, String username);
}
