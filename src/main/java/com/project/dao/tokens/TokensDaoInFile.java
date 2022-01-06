package com.project.dao.tokens;

import com.project.models.Token;
import com.project.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("TokensDaoInFile")
public class TokensDaoInFile implements TokensDao{
    @Override
    public Optional<Token> find(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Token model) {

    }

    @Override
    public void update(Token model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Token> findAll() {
        return null;
    }

    @Override
    public Optional<Token> findOneByValue(String value) {
        return Optional.empty();
    }

    @Override
    public List<Token> findAllByUser(User user) {
        return null;
    }
}
