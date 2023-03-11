package com.project.dao.tokens;

import com.project.dao.CrudDao;
import com.project.models.Token;
import com.project.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes methods for working with tokens in the storage
 *
 * @see CrudDao
 */
public interface TokensDao extends CrudDao<Token> {
    /**
     * Method finds token by value
     *
     * @param value token value
     * @return an object of type {@link Optional} for {@link Token}
     */
    Optional<Token> findOneByValue(String value);

    /**
     * Method finds all tokens by user
     *
     * @param user token owner
     * @return token list as {@link List}
     */
    List<Token> findAllByUser(User user);
}
