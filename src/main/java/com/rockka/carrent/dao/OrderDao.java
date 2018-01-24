package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    public Order getById(long id);
    public List<Order> getAllWithUser(String username);
}
