package com.project.controllers;

import com.project.forms.UserForm;
import com.project.services.UsersService;
import com.project.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for handling requests related to users
 */
@RestController
public class UsersController {

    /**
     * Field for accessing users service methods
     */
    @Autowired
    private UsersService usersService;

    /**
     * Method for handling a request to add a user
     * @param userForm form like {@link UserForm}
     * @return successful execution of a request with a response body in the form of a user DTO from {@link UserDto}
     * @see UsersService#addUser(UserForm) 
     */
    @PostMapping("/registration")
    public ResponseEntity<UserDto> addUser(@RequestBody UserForm userForm) {
        return ResponseEntity.ok((usersService.addUser(userForm)));
    }
}
