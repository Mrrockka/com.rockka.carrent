package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Order;

public interface OrderDao extends GenericDao<Order> {
    public Order getById(long id);
}
