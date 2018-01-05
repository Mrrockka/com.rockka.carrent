package com.rockka.carrent.dao;

import java.util.List;

public interface GenericDao <T>{
    public T save(T o);
    public T delete(T o);
    public List<T> getAll(Class<T> type);
}
