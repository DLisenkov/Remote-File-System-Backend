package com.project.dao.users;

import com.project.dao.CrudDao;
import com.project.models.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface UsersDao extends CrudDao<User> {
    Optional<User> findOneByLogin(String login);
}
