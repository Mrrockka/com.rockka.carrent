package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByNickname(String nickname) {
        return userDao.getUserByNickname(nickname);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public boolean isExists(User user) {
        if(userDao.getUserByNickname(user.getNickname()) != null){
            return true;
        }
        return false;
    }
}
