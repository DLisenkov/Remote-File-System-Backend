package com.project.dao.users;

import com.project.dao.CrudDao;
import com.project.models.User;
import com.project.single.UsersStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Class for working with storage of users in memory that implements the {@link UsersDao} interface
 *
 * @see UsersStorage
 */
@Component("UsersDaoInMemory")
public class UsersDaoInMemory implements UsersDao {

    /**
     * Implements the method {@link com.project.dao.CrudDao#find(int)}
     *
     * @param id unique identificator
     * @return an object of type {@link Optional} for {@link User}
     */
    @Override
    public Optional<User> find(int id) {
        for (User user : UsersStorage.storage().users()) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#save(Object)}
     *
     * @param model data to save
     */
    @Override
    public void save(User model) {
        UsersStorage.storage().users().add(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#update(Object)}
     *
     * @param model data to update
     */
    @Override
    public void update(User model) {
        for (User user : UsersStorage.storage().users()) {
            if (user.getId() == model.getId()) {
                UsersStorage.storage().users().set(user.getId(), model);
            }
        }
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#delete(int)}
     *
     * @param id unique identificator
     */
    @Override
    public void delete(int id) {
        UsersStorage.storage().users().removeIf(user -> user.getId() == id);
    }

    /**
     * Implements the method {@link CrudDao#findAll()}
     *
     * @return user list as {@link List}
     */
    @Override
    public List<User> findAll() {
        return UsersStorage.storage().users();
    }

    /**
     * Implements the method {@link UsersDao#findOneByLogin(String)}
     *
     * @param login user login
     * @return an object of type {@link Optional} for {@link User}
     */
    @Override
    public Optional<User> findOneByLogin(String login) {
        for (User user : UsersStorage.storage().users()) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
