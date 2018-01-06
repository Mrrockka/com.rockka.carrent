package com.rockka.carrent.services;

import com.rockka.carrent.dao.impl.UserDaoImp;
import com.rockka.carrent.domain.User;

public interface UserService {
    public User getUserByNeame(String name);
}
