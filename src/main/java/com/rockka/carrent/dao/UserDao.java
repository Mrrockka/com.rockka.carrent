package com.rockka.carrent.dao;

import com.rockka.carrent.domain.User;

public interface UserDao extends GenericDao<User>{
    public User getUserByUsername(String name);
}
