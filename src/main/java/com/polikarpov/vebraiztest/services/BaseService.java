package com.polikarpov.vebraiztest.services;

public interface BaseService<T, K> {

    T create(T entity);

    T find(K id);

    boolean update(K id, T entity);

    boolean delete(K id);

}
