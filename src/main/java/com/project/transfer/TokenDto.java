package com.project.transfer;

import com.project.models.Token;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Token data transfer object
 */
@Data
@AllArgsConstructor
public class TokenDto {

    /**
     * Token value
     */
    private String value;

    /**
     * Method for getting token DTO by token
     * @param token token
     * @return token DTO like {@link TokenDto}
     */
    public static TokenDto from(Token token) {
        return new TokenDto(token.getValue());
    }
}