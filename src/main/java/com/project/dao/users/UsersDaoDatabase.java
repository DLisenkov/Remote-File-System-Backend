package com.project.dao.users;

import com.project.dao.CrudDao;
import com.project.models.User;
import com.project.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Class for working with a user database that implements the {@link UsersDao} interface
 */
@Component ("UsersDaoDatabase")
public class UsersDaoDatabase implements UsersDao {

    /**
     * Field for accessing users repository methods
     * @see UsersRepository
     */
    @Autowired
    UsersRepository usersRepository;

    /**
     * Implements the method {@link com.project.dao.CrudDao#find(int)}
     * @param id unique identificator
     * @return an object of type {@link Optional} for {@link User}
     * @see UsersRepository#findById(Object)
     */
    @Override
    public Optional<User> find(int id) {
        return usersRepository.findById(id);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#save(Object)}
     * @param model data to save
     * @see UsersRepository#save(Object)
     */
    @Override
    public void save(User model) {
        usersRepository.save(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#update(Object)}
     * @param model data to update
     * @see UsersRepository#save(Object)
     */
    @Override
    public void update(User model) {
        usersRepository.save(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#delete(int)}
     * @param id unique identificator
     * @see UsersRepository#deleteById(Object)
     */
    @Override
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    /**
     * Implements the method {@link CrudDao#findAll()}
     * @return token list as {@link List}
     * @see UsersRepository#findAll()
     */
    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    /**
     * Implements the method {@link UsersDao#findOneByLogin(String)}
     * @param login user login
     * @return an object of type {@link Optional} for {@link User}
     */
    @Override
    public Optional<User> findOneByLogin(String login) {
        return usersRepository.findOneByLogin(login);
    }
}
