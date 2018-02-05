package com.rockka.carrent.dao;

import com.rockka.carrent.domain.User;
/*
 ** Interface for database user selections
 */
public interface UserDao extends GenericDao<User>{
    /*
     ** Selecting user entity by username
     */
    public User getByUsername(String name);
}
