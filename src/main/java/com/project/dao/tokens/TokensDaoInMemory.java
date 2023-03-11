package com.project.dao.tokens;

import com.project.dao.CrudDao;
import com.project.models.Token;
import com.project.models.User;
import com.project.single.TokensStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Class for working with storage of tokens in memory that implements the {@link TokensDao} interface
 *
 * @see TokensStorage
 */
@Component("TokensDaoInMemory")
public class TokensDaoInMemory implements TokensDao {

    /**
     * Implements the method {@link com.project.dao.CrudDao#find(int)}
     *
     * @param id unique identificator
     * @return an object of type {@link Optional} for {@link Token}
     */
    @Override
    public Optional<Token> find(int id) {
        for (Token token : TokensStorage.storage().tokens()) {
            if (token.getId() == id) {
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#save(Object)}
     *
     * @param model data to save
     */
    @Override
    public void save(Token model) {
        TokensStorage.storage().tokens().add(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#update(Object)}
     *
     * @param model data to update
     */
    @Override
    public void update(Token model) {
        for (Token token : TokensStorage.storage().tokens()) {
            if (token.getId() == model.getId()) {
                TokensStorage.storage().tokens().set(token.getId(), model);
            }
        }
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#delete(int)}
     *
     * @param id unique identificator
     */
    @Override
    public void delete(int id) {
        TokensStorage.storage().tokens().removeIf(token -> token.getId() == id);
    }

    /**
     * Implements the method {@link CrudDao#findAll()}
     *
     * @return token list as {@link List}
     */
    @Override
    public List<Token> findAll() {
        return TokensStorage.storage().tokens();
    }

    /**
     * Implements the method {@link TokensDao#findOneByValue(String)}
     *
     * @param value token value
     * @return an object of type {@link Optional} for {@link Token}
     */
    @Override
    public Optional<Token> findOneByValue(String value) {
        for (Token token : TokensStorage.storage().tokens()) {
            if (token.getValue().equals(value)) {
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

    /**
     * Implements the method {@link TokensDao#findAllByUser(User)}
     *
     * @param user token owner
     * @return token list as {@link List}
     */
    @Override
    public List<Token> findAllByUser(User user) {
        return null;
    }
}
