package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService{
    @Autowired
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
    @Override
    public User getByUsername(String nickname) {
        return userDao.getByUsername(nickname);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User save(User user) {
        if(user != null ) {
            if(!isExists(user)) {
                userDao.save(user.setModifiedAt(new Date()));
            } else {
                logger.error("UserServiceImp: USER IS EXISTS!");
            }
        } else{
            logger.error("UserServiceImp: USER IS NULL");
        }
        return user;
    }

    @Override
    public User update(User user) {
        if (user != null) {
            if(isExists(user)) {
                userDao.update(user.setModifiedAt(new Date()));
            } else {
                logger.error("UserServiceImp: USER IS NOT EXISTS!");
            }
        } else{
            logger.error("UserServiceImp: USER IS NULL");
        }
        return user;
    }

    @Override
    public User delete(User user) {
        if(user != null) {
            update(user.setUserStatus(0));
        } else{
            logger.error("UserServiceImp: USER IS NULL");
        }
        return user;
    }

    @Override
    public boolean isExists(User user) {
        if(userDao.getByUsername(user.getUsername()) != null){
            return true;
        }
        return false;
    }
}
