package net;

import persistence.user.User;

/**
 * The response to a user profile request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class UserProfileRequestResponse extends RequestResponse {
    public final User user;

    public UserProfileRequestResponse(User user) {
        super(RequestType.GET_USER);
        this.user = user;
    }
}
