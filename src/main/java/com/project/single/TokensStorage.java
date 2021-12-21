package com.project.single;

import com.project.models.Token;

import java.util.ArrayList;
import java.util.List;

public class TokensStorage {
    private static final TokensStorage storage;

    static {
        storage = new TokensStorage();
    }

    private List<Token> tokens;

    private TokensStorage() {
        tokens = new ArrayList<>();
    }

    public static TokensStorage storage() {
        return storage;
    }

    public List<Token> tokens() {
        return tokens;
    }
}
