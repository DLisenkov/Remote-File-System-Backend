package com.project.repositories;

import com.project.models.Token;
import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Tokens repository interface extends {@link JpaRepository} interface uses hibernate to work with token database
 */
public interface TokensRepository extends JpaRepository<Token, Integer> {

    /**
     * Method finds token by value
     * @param value token value
     * @return an object of type {@link Optional} for {@link Token}
     */
    Optional<Token> findOneByValue(String value);

    /**
     * Method finds all tokens by user
     * @param user token owner
     * @return token list as {@link List}
     */
    List<Token> findAllByUser(User user);
}
