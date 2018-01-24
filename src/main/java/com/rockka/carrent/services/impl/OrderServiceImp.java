package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.OrderDao;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Override
    public Order getById(long id) {
        if(id > 0) {
            return orderDao.getById(id);
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
    @Override
    public List<Order> getAllWithUser(String username){
        if (username != null || !username.equals("")) {
            return orderDao.getAllWithUser(username);
        }
        return null;
    }

    @Override
    public Order save(Order order) {
        if(order != null){
            orderDao.save(order);
        }
        return order;
    }

    @Override
    public Order delete(Order order) {
        if(order != null) {
            update(order.setOrderStatus(0));
        }
        return order;
    }

    @Override
    public Order update(Order order){
        if(order != null) {
            orderDao.update(order.setModifiedAt(new Date()));
        }
        return order;
    }
}
