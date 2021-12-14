package com.project.dao;

import com.project.models.User;

import java.util.List;
import java.util.Optional;

public class UsersDaoInFile implements UsersDao{
    @Override
    public Optional<User> find(int id) {
        return Optional.empty();
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
