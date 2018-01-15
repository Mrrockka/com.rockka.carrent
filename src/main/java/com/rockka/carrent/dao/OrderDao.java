package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Order;

import java.util.List;

public interface OrderDao {
    public Order getById(long id);
    public List<Order> getAll();
    public Order save(Order order);
    public Order delete(Order order);

}
