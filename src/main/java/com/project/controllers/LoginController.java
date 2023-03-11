package com.project.controllers;

import com.project.forms.LoginForm;
import com.project.services.LoginService;
import com.project.transfer.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for handling requests related to login
 */
@RestController
public class LoginController {

    /**
     * Field for accessing login service methods
     */
    @Autowired
    private LoginService loginService;

    /**
     * Method of handling the request for login
     *
     * @param loginForm form like {@link LoginForm}
     * @return successful execution of a request with a response body in the form of a token DTO from {@link TokenDto}
     * @see LoginService#login(LoginForm)
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}
