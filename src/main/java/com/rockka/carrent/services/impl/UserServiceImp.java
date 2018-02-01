package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import org.joda.time.LocalDateTime;
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
    public User getByUsername(String username) {
        if(username != null && !username.equals("")) {
            return userDao.getByUsername(username);
        } else {
            logger.error("Username is null");
        }
         return null;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User save(User user) {
        if(user != null ) {
            if(!isExists(user)) {
                userDao.save(user);
            } else {
                logger.error("USER IS EXISTS!");
            }
        } else{
            logger.error("USER IS NULL");
        }
        return user;
    }

    @Override
    public User update(User user) {
        if (user != null) {
            if(isExists(user)) {
                userDao.update(user.setModifiedAt(LocalDateTime.now()));
            } else {
                logger.error("USER IS NOT EXISTS!");
            }
        } else{
            logger.error("USER IS NULL");
        }
        return user;
    }

    @Override
    public User delete(User user) {
        if(user != null) {
            update(user.setUserStatus(0));
        } else{
            logger.error("USER IS NULL");
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
