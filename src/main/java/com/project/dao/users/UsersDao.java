package com.project.dao.users;

import com.project.dao.CrudDao;
import com.project.models.User;

import java.util.Optional;

/**
 * Interface describes methods for working with users in the storage
 * @see CrudDao
 */
public interface UsersDao extends CrudDao<User> {
    /**
     * Method finds the user by login
     * @param login user login
     * @return an object of type {@link Optional} for {@link User}
     */
    Optional<User> findOneByLogin(String login);
}
