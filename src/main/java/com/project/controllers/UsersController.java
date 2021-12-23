package com.project.controllers;

import com.project.forms.UserForm;
import com.project.services.UsersService;
import com.project.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/registration")
    public ResponseEntity<UserDto> addUser(@RequestBody UserForm userForm) {
        return ResponseEntity.ok((usersService.addUser(userForm)));
    }
}
