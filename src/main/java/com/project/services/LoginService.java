package com.project.services;

import com.project.forms.LoginForm;
import com.project.transfer.TokenDto;

/**
 * Service interface for implementing the logic of the program associated with login
 */
public interface LoginService {

    /**
     * Method that logs the user into the system
     * @param loginForm form like {@link LoginForm}
     * @return token DTO like {@link TokenDto}
     */
    TokenDto login(LoginForm loginForm);
}
