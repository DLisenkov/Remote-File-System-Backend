package com.project.dao;


import com.project.models.User;
import com.project.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component ("UsersDaoDatabase")
public class UsersDaoDatabase implements UsersDao {

    @Autowired
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
