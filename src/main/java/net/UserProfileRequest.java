package net;

/**
 * A user profile request is a request for a specific users
 * personal details including name, age, dob etc.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class UserProfileRequest extends Request {
    public final String username;

    public UserProfileRequest(String username) {
        super(RequestType.GET_USER);
        this.username = username;
    }
}
