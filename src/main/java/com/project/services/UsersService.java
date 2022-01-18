package com.project.services;

import com.project.forms.UserForm;
import com.project.transfer.UserDto;

/**
 * Service interface for implementing the logic of the program associated with users
 */
public interface UsersService {

    /**
     * Path field for loading from the environment variable
     */
    String LOAD_PATH = System.getenv("LOAD_PATH") + "/";

    /**
     * Method for adding a user to the data storage
     * @param userForm form like {@link UserForm}
     * @return user dto like {@link UserDto}
     */
    UserDto addUser(UserForm userForm);
}
