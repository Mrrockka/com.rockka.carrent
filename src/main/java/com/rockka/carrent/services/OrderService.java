package com.rockka.carrent.services;

import com.rockka.carrent.dao.OrderDao;
import com.rockka.carrent.domain.Order;

import java.util.List;

public interface OrderService extends OrderDao{
	public List<Order> getAllWithUser(String username);
	public List<Order> getAllWithCar(long carId);
}
