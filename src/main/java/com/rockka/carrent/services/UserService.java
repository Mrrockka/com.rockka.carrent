package com.rockka.carrent.services;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.domain.User;

import java.util.List;

public interface UserService extends UserDao {
    public boolean isExists(User user);
}
