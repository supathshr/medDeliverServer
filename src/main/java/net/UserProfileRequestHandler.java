package net;

import persistence.user.UserGrabber;

/**
 * Handles a user profile request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class UserProfileRequestHandler extends RequestHandler<UserProfileRequest> {

    @Override
    public RequestResponse handle(UserProfileRequest message) {
        return new UserProfileRequestResponse(UserGrabber.get(message.username));
    }
}
