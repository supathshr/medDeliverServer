package persistence;

import persistence.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AuthenticatedUsers {
    private static AuthenticatedUsers current;

    private Map<String, User> users = new HashMap<>();

    public AuthenticatedUsers() {}

    public void add(String authKey, User user) {
        users.put(authKey, user);
    }

    public void remove(String authKey) {
        users.remove(authKey);
    }

    public User get(String authKey) {
        return users.get(authKey);
    }

    public static String generateAuthKey() {
        return String.valueOf(ThreadLocalRandom.current().nextInt());
    }

    public static AuthenticatedUsers getInstance() {
        if(current == null) {
            current = new AuthenticatedUsers();
        }
        return current;
    }

}
