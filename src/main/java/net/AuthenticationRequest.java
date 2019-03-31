package net;

/**
 * The Authentication request object.
 * This is used to Login a user.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class AuthenticationRequest extends Request {
    /**
     * The attempted username for login
     */
    public final String username;
    /**
     * The attempted password for login
     */
    public final String password;

    public AuthenticationRequest(String username, String password) {
        super(RequestType.AUTHENTICATION);
        this.username = username;
        this.password = password;
    }
}
