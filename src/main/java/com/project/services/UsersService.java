package com.project.services;

import com.project.forms.UserForm;
import com.project.transfer.UserDto;

public interface UsersService {

    String LOAD_PATH = System.getenv("LOAD_PATH") + "/";

    UserDto addUser(UserForm registrationForm);
}
