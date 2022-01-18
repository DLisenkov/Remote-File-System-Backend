package com.project.repositories;

import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Users repository interface extends {@link JpaRepository} interface uses hibernate to work with user database
 */
public interface UsersRepository  extends JpaRepository<User, Integer> {
    /**
     * Method finds the user by login
     * @param login user login
     * @return an object of type {@link Optional} for {@link User}
     */
    Optional<User> findOneByLogin(String login);
}
