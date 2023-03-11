package com.project.single;

import com.project.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing users in memory
 */
public class UsersStorage {

    /**
     * {@link UsersStorage} filed
     */
    private static final UsersStorage storage;

    static {
        storage = new UsersStorage();
    }

    /**
     * List of users
     *
     * @see List
     */
    private List<User> users;

    /**
     * Parameterless constructor creates a list of users
     *
     * @see ArrayList
     */
    private UsersStorage() {
        users = new ArrayList<>();
    }

    /**
     * Static method for getting storage
     *
     * @return storage
     * @see UsersStorage#storage
     */
    public static UsersStorage storage() {
        return storage;
    }

    /**
     * Method of getting users from storage
     *
     * @return users
     * @see UsersStorage#users
     */
    public List<User> users() {
        return users;
    }
}
