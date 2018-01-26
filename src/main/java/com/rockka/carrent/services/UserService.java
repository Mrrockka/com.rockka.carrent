package com.rockka.carrent.services;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;

public interface UserService extends UserDao {
    public boolean isExists(User user);
}
