package single;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersStorage {

    private static final UsersStorage storage;

    static {
        storage = new UsersStorage();
    }

    private List<User> users;

    private UsersStorage() {
        users = new ArrayList<>();
    }

    public static UsersStorage storage() {
        return storage;
    }

    public List<User> users() {
        return users;
    }
}
