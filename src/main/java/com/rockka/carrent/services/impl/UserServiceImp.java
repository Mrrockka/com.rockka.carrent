package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.enums.UserStatus;
import com.rockka.carrent.services.UserService;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 ** UserDao proxy
 */
@Service("userService")
public class UserServiceImp implements UserService{
    @Autowired
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
    /*
     ** Checks variables before dao call
     */
    @Override
    public User getByUsername(String username) {
        if(username != null && !username.equals("")) {
            return userDao.getByUsername(username);
        } else {
            logger.error("Username is null");
        }
         return null;
    }
    /*
     ** Calling dao method
     */
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
    /*
     ** Checks variables before dao call
     */
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
    /*
     ** Checks variables before dao call
     */
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
    /*
     ** Checks variables and setts invoice status to deleted (Not deleting entity from DB)
     */
    @Override
    public User delete(User user) {
        if(user != null) {
            update(user.setStatus(UserStatus.DELETED));
        } else{
            logger.error("USER IS NULL");
        }
        return user;
    }
    /*
     ** Checking is user already exist
     */
    @Override
    public boolean isExists(User user) {
        if(userDao.getByUsername(user.getUsername()) != null){
            return true;
        }
        return false;
    }
}
