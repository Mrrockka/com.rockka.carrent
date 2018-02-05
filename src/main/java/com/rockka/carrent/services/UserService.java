package com.rockka.carrent.services;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;

/*
 ** UserDao proxy
 */
public interface UserService extends UserDao {
    /*
     ** Checking is user already exist
     */
    public boolean isExists(User user);
}
