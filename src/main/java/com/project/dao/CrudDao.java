package com.project.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing the basic CRUD operations for working with data
 * @param <T> data type
 */
public interface CrudDao<T> {
    /**
     * Method looks for data by id
     * @param id unique identificator
     * @return {@link Optional} type object
     */
    Optional<T> find(int id);

    /**
     * Method saves data to storage
     * @param model data to save
     */
    void save(T model);

    /**
     * Method updates data in storage
     * @param model data to update
     */
    void update(T model);

    /**
     * Method deletes data in storage
     * @param id unique identificator
     */
    void delete(int id);

    /**
     * Method finds all the data in storage
     * @return data list as {@link List}
     */
    List<T> findAll();
}
