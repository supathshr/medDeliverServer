package persistence.logins;

import hibernate.HibernateUtil;
import persistence.user.User;

import java.util.HashMap;
import java.util.List;

/**
 * This class is used to authenticate user login.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class LoginAuthenticator {
    /**
     * Checks if a username and password combination exists in the database
     *
     * @param username The username of the User
     * @param password The encrypted password of the User
     * @return The user object if the User is authenticated otherwise null
     */
    public static User authenticate(String username, String password) {
        final List<?> result = HibernateUtil.queryDatabase("from User WHERE username=:username AND password=:password", new HashMap<String, Object>() {{
            put("username", username);
            put("password", password);
        }});

        if (result.size() <= 0) {
            System.out.println(username + " not authenticated, invalid credentials.");
            return null;
        }

        return (User) result.get(0);
    }
}
