package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.OrderDao;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Override
    public Order getById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order save(Order order) {
        return orderDao.save(order);
    }

    @Override
    public Order delete(Order order) {
        return orderDao.delete(order);
    }
}
