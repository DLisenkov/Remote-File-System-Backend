package com.project.security.provider;

import com.project.dao.tokens.TokensDao;
import com.project.models.Token;
import com.project.security.token.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Provider to implement your own token authentication implements {@link AuthenticationProvider}
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    /**
     * Field for accessing tokens DAO methods
     *
     * @see TokensDao
     */
    @Autowired
    @Qualifier("TokensDaoDatabase")
    private TokensDao tokensDao;

    /**
     * Field for accessing user details service methods
     *
     * @see UserDetailsService
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Method checks the validity of the token and sets up the authentication
     *
     * @param authentication authentication object
     * @return token authentication object
     * @throws IllegalArgumentException if the token is not valid
     * @see TokensDao#findOneByValue
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws IllegalArgumentException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        Optional<Token> tokenCandidate = tokensDao.findOneByValue(tokenAuthentication.getName());

        if (tokenCandidate.isPresent()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenCandidate.get().getUser().getLogin());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else throw new IllegalArgumentException("Bad token");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
