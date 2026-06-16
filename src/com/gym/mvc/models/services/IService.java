package com.gym.mvc.models.services;

import java.util.List;

public interface IService<T> {

    void store(T entity);

    List<T> getAll();

    T findById(int id);

    void update(T entity);

    void destroy(int id);

}