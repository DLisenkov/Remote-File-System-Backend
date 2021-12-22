package com.project.dao.tokens;

import com.project.models.Token;
import com.project.repositories.TokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("TokensDaoDatabase")
public class TokensDaoDatabase implements TokensDao{

    @Autowired
    private TokensRepository tokensRepository;

    @Override
    public Optional<Token> find(int id) {
        return tokensRepository.findById(id);
    }

    @Override
    public void save(Token model) {
        tokensRepository.save(model);
    }

    @Override
    public void update(Token model) {
        tokensRepository.save(model);
    }

    @Override
    public void delete(int id) {
        tokensRepository.deleteById(id);
    }

    @Override
    public List<Token> findAll() {
        return tokensRepository.findAll();
    }

    @Override
    public Optional<Token> findOneByValue(String value) {
        return tokensRepository.findOneByValue(value);
    }
}
