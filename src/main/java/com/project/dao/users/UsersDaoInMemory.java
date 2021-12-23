package com.project.dao.users;

import com.project.models.User;
import com.project.single.UsersStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component ("UsersDaoInMemory")
public class UsersDaoInMemory implements UsersDao {
    @Override
    public Optional<User> find(int id) {
        for(User user: UsersStorage.storage().users()) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(User model) {
        UsersStorage.storage().users().add(model);
    }

    @Override
    public void update(User model) {
        for(User user: UsersStorage.storage().users()) {
            if (user.getId() == model.getId()) {
                UsersStorage.storage().users().set(user.getId(), model);
            }
        }
    }

    @Override
    public void delete(int id) {
        UsersStorage.storage().users().removeIf(user -> user.getId() == id);
    }

    @Override
    public List<User> findAll() {
        return UsersStorage.storage().users();
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        for(User user: UsersStorage.storage().users()) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
