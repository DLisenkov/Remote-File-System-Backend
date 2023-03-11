package com.project.forms;

import lombok.Data;

/**
 * The form contains login data
 *
 * @see lombok.Data
 */
@Data
public class LoginForm {

    /**
     * User login
     */
    private String login;

    /**
     * User password
     */
    private String password;
}
