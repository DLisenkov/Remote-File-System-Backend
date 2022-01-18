package com.project.dao.tokens;

import com.project.dao.CrudDao;
import com.project.models.Token;
import com.project.models.User;
import com.project.repositories.TokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Class for working with a token database that implements the {@link TokensDao} interface
 */
@Component("TokensDaoDatabase")
public class TokensDaoDatabase implements TokensDao{

    /**
     * Field for accessing tokens repository methods
     * @see TokensRepository
     */
    @Autowired
    private TokensRepository tokensRepository;

    /**
     * Implements the method {@link com.project.dao.CrudDao#find(int)}
     * @param id unique identificator
     * @return an object of type {@link Optional} for {@link Token}
     * @see TokensRepository#findById(Object)
     */
    @Override
    public Optional<Token> find(int id) {
        return tokensRepository.findById(id);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#save(Object)}
     * @param model data to save
     * @see TokensRepository#save(Object)
     */
    @Override
    public void save(Token model) {
        tokensRepository.save(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#update(Object)}
     * @param model data to update
     * @see TokensRepository#save(Object)
     */
    @Override
    public void update(Token model) {
        tokensRepository.save(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#delete(int)}
     * @param id unique identificator
     * @see TokensRepository#deleteById(Object)
     */
    @Override
    public void delete(int id) {
        tokensRepository.deleteById(id);
    }

    /**
     * Implements the method {@link CrudDao#findAll()}
     * @return token list as {@link List}
     * @see TokensRepository#findAll()
     */
    @Override
    public List<Token> findAll() {
        return tokensRepository.findAll();
    }

    /**
     * Implements the method {@link TokensDao#findOneByValue(String)}
     * @param value token value
     * @return an object of type {@link Optional} for {@link Token}
     * @see TokensRepository#findOneByValue(String)
     */
    @Override
    public Optional<Token> findOneByValue(String value) {
        return tokensRepository.findOneByValue(value);
    }

    /**
     * Implements the method {@link TokensDao#findAllByUser(User)}
     * @param user token owner
     * @return token list as {@link List}
     * @see TokensRepository#findAllByUser(User)
     */
    @Override
    public List<Token> findAllByUser(User user) {
        return tokensRepository.findAllByUser(user);
    }
}
