package com.project.dao.tokens;

import com.project.dao.CrudDao;
import com.project.models.Token;

import java.util.Optional;

public interface TokensDao extends CrudDao<Token> {
    Optional<Token> findOneByValue(String value);
}
