package persistence;

import persistence.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class handles authenticated users and their authentication keys.
 * Authentication keys are non-persistent and reside in memory until app termination.
 * <p>
 * In the event that a user attempts to authenticate using an old authentication key,
 * they will need to re-authenticate themselves by logging in.
 * <p>
 * Note: Auth keys, once generated can be used by anybody however operations performed using an auth key
 * can be tracked to the registered user. As such, the auth key needs to be protected by user.
 * <p>
 * The <a href="https://en.wikipedia.org/wiki/Singleton_pattern">Singleton Design Pattern</a> is used because to
 * access this class i.e. {@code AuthenticatedUsers#getInstance} because multiple instances of this class will
 * will not differ from having just one.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class AuthenticatedUsers {
    private static AuthenticatedUsers current;

    /**
     * This Map contains the authentication keys and associated {@link persistence.user.User}s
     */
    private final Map<String, User> users = new HashMap<>();

    private AuthenticatedUsers() {
    }

    /**
     * Assigns an auth key to a specific user.
     * Once an auth key has been added, it will be available to be used
     * for requests until removed.
     *
     * @param authKey The auth key, there are no constraints however this best practices for passwords
     *                should be used when not generated using {@link AuthenticatedUsers#generateAuthKey()}.
     * @param user    The {@link persistence.user.User} the auth key is binded to.
     *                Optionally this can be null if adding a generic non-trackable key.
     */
    public void add(String authKey, User user) {
        users.put(authKey, user);
    }

    /**
     * Checks if the authentication exists in the system and can be used for requests.
     *
     * @param authKey The authentication key to check.
     * @return True if the auth key is usable.
     */
    public boolean exists(String authKey) {
        return users.containsKey(authKey);
    }

    /**
     * Removes an auth key so it can no longer be used.
     *
     * @param authKey The auth key to remove.
     */
    public void remove(String authKey) {
        users.remove(authKey);
    }

    /**
     * Get the {@link persistence.user.User} associated with the authKey.
     *
     * @param authKey The assigned auth key.
     * @return The {@link persistence.user.User}  object.
     */
    public User get(String authKey) {
        return users.get(authKey);
    }

    /**
     * Creates a random authentication key.
     *
     * @return The new auth key that can be used to make requests.
     */
    public static String generateAuthKey() {
        return String.valueOf(ThreadLocalRandom.current().nextInt());
    }

    /**
     * A singleton to access this class.
     * This method uses lazy initialisation.
     *
     * @return The {@link AuthenticatedUsers} instance.
     */
    public static AuthenticatedUsers getInstance() {
        if (current == null) {
            current = new AuthenticatedUsers();
        }
        return current;
    }

}
