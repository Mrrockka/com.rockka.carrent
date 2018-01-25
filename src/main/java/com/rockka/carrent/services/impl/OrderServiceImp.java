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
    public List<Order> getAllWithUser(String username) {
        return getAllWithUser(username, false);

    }

    @Override
    public List<Order> getAllWithUser(String username, boolean withDeleted) {
        if(username != null && !username.equals("")){
            return orderDao.getAllWithUser(username, withDeleted);
        }
        return null;
    }

    @Override
    public List<Order> getAllWithCar(long carId, boolean withDeleted) {
        if(carId >0){
            orderDao.getAllWithCar(carId, withDeleted);
        }
        return null;
    }

    @Override
    public Order getByIdWithUser(long orderId, String username) {
        if(orderId>0 && username != null && !username.equals("")){
            return orderDao.getByIdWithUser(orderId, username);
        }
        return null;
    }

    @Override
    public List<Order> getAllWithCar(long carId) {
        return getAllWithCar(carId, false);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
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
