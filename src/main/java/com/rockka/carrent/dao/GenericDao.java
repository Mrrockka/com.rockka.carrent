package com.rockka.carrent.dao;

import java.util.List;
/*
 ** Generic for database invoice selections
 */
public interface GenericDao <T>{
    /*
     ** Save operation
     */
    public T save(T o);
    /*
     ** Update operation
     */
    public T update(T o);
    /*
     ** Delete operation
     */
    public T delete(T o);
    /*
     ** Selecting all entities
     */
    public List<T> getAll();
}
