package com.rockka.carrent.dao;

import com.rockka.carrent.domain.User;

import java.util.List;

public interface UserDao {
    public User getUserByName(String name);
    public List<User> getAll();
    public User save(User user);
    public User delete(User user);
}
