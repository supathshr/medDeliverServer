package net;

import persistence.AuthenticatedUsers;
import persistence.logins.LoginAuthenticator;
import persistence.user.User;

/**
 * Handles an authentication request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class AuthenticationRequestHandler extends RequestHandler<AuthenticationRequest> {

    @Override
    public RequestResponse handle(AuthenticationRequest message) {
        final String username = message.username;
        final String password = message.password;

        final User user = LoginAuthenticator.authenticate(username, password);

        if (null == user) {
            return new AuthenticationResponse("", false);
        }

        final String newAuthKey = AuthenticatedUsers.generateAuthKey();

        AuthenticatedUsers.getInstance().add(newAuthKey, user);

        return new AuthenticationResponse(newAuthKey, true);
    }
}
