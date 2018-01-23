package com.rockka.carrent.dao;

import com.rockka.carrent.domain.User;

public interface UserDao extends GenericDao<User>{
    public User getByUsername(String name);
}
