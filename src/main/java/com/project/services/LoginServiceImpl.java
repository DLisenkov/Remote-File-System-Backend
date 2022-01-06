package com.project.services;

import com.project.dao.tokens.TokensDao;
import com.project.dao.users.UsersDao;
import com.project.forms.LoginForm;
import com.project.models.Token;
import com.project.models.User;
import com.project.transfer.TokenDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    @Qualifier("UsersDaoDatabase")
    private UsersDao usersDao;

    @Autowired
    @Qualifier("TokensDaoDatabase")
    private TokensDao tokensDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = usersDao.findOneByLogin(loginForm.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                List<Token> oldTokens = tokensDao.findAllByUser(user);
                for (Token oldToken: oldTokens) {
                    tokensDao.delete(oldToken.getId());
                }
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();
                tokensDao.save(token);
                return TokenDto.from(token);
            }
        } throw new IllegalArgumentException("User not found");
    }
}
