package net;

/**
 * Decodes a user profile request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class UserProfileRequestDecoder extends RequestDecoder<UserProfileRequest> {

    @Override
    public UserProfileRequest decode(String json) {
        return new UserProfileRequest(getString(json, "username"));
    }
}
