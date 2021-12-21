package com.project.dao.tokens;

import com.project.models.Token;
import com.project.single.TokensStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("TokensDaoInMemory")
public class TokensDaoInMemory implements TokensDao{
    @Override
    public Optional<Token> find(int id) {
        for(Token token: TokensStorage.storage().tokens()) {
            if (token.getId() == id) {
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(Token model) {
        TokensStorage.storage().tokens().add(model);
    }

    @Override
    public void update(Token model) {
        for(Token token: TokensStorage.storage().tokens()) {
            if (token.getId() == model.getId()) {
                TokensStorage.storage().tokens().set(token.getId(), model);
            }
        }
    }

    @Override
    public void delete(int id) {
        TokensStorage.storage().tokens().removeIf(token -> token.getId() == id);
    }

    @Override
    public List<Token> findAll() {
        return TokensStorage.storage().tokens();
    }
}
