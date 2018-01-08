package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImp implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByNeame(String name) {
        return userDao.getUserByNickname(name);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
