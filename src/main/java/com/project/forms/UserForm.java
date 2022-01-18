package com.project.forms;

import lombok.Data;

/**
 * The form contains user data
 * @see lombok.Data
 */
@Data
public class UserForm {

    /**
     * User first name
     */
    private String firstName;

    /**
     * User last name
     */
    private String lastName;

    /**
     * User login
     */
    private String login;

    /**
     * User password
     */
    private String password;
}