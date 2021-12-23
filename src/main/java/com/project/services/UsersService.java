package com.project.services;

import com.project.forms.UserForm;
import com.project.transfer.UserDto;

public interface UsersService {
    UserDto addUser(UserForm registrationForm);
}
