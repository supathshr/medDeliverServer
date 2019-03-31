package persistence.user;

import hibernate.HibernateUtil;

import java.util.HashMap;
import java.util.List;

/**
 * This class is used to grab User information from the database.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class UserGrabber {

    /**
     * Checks if the passed username is present within the database
     *
     * @param username The username to check
     * @return True if the username is present
     */
    public static boolean exists(String username) {
        final List<?> result = HibernateUtil.queryDatabase("from User WHERE username=:username", new HashMap<String, Object>() {{
            put("username", username);
        }});

        return result.size() > 0;
    }

    /**
     * Gets the User by username from the database and returns a User object
     *
     * @param username The username to search
     * @return The User object otherwise null if the username does not exist
     */
    public static User get(String username) {
        final List<?> result = HibernateUtil.queryDatabase("from User WHERE username=:username", new HashMap<String, Object>() {{
            put("username", username);
        }});

        if (result.size() <= 0) {
            System.out.println("Username " + username + " not found.");
            return null;
        }

        return (User) result.get(0);
    }

    /**
     * Gets a User from the database by id
     *
     * @param id The user id in the database
     * @return The User object if the Id exists otherwise null
     */
    public static User get(long id) {
        final List<?> result = HibernateUtil.queryDatabase("from User WHERE id=:id", new HashMap<String, Object>() {{
            put("id", id);
        }});

        if (result.size() <= 0) {
            System.out.println("No user for id " + id + " found.");
            return null;
        }

        return (User) result.get(0);
    }
}
