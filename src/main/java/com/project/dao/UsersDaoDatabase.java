package com.project.dao;


import com.project.models.User;
import com.project.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

public class UsersDaoDatabase implements UsersDao {

    UsersRepository usersRepository;

    @Override
    public Optional<User> find(int id) {
        return usersRepository.findById(id);
    }

    @Override
    public void save(User model) {
        usersRepository.save(model);
    }

    @Override
    public void update(User model) {
        usersRepository.save(model);
    }

    @Override
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }
}
