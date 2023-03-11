package com.project.single;

import com.project.models.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing tokens in memory
 */
public class TokensStorage {

    /**
     * {@link TokensStorage} filed
     */
    private static final TokensStorage storage;

    static {
        storage = new TokensStorage();
    }

    /**
     * List of tokens
     *
     * @see List
     */
    private List<Token> tokens;

    /**
     * Parameterless constructor creates a list of tokens
     *
     * @see ArrayList
     */
    private TokensStorage() {
        tokens = new ArrayList<>();
    }

    /**
     * Static method for getting storage
     *
     * @return storage
     * @see TokensStorage#storage
     */
    public static TokensStorage storage() {
        return storage;
    }

    /**
     * Method of getting tokens from storage
     *
     * @return tokens
     * @see TokensStorage#tokens
     */
    public List<Token> tokens() {
        return tokens;
    }
}
