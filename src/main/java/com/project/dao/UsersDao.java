package com.project.dao;

import com.project.models.User;
import org.springframework.stereotype.Component;

public interface UsersDao extends CrudDao<User>{
    //List<User> findAllByFirstName(String firstName);
}
