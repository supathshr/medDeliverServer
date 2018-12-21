package net;

import persistence.AuthenticatedUsers;
import persistence.logins.LoginAuthenticator;
import persistence.user.User;
import persistence.user.UserGrabber;

public class AuthenticationRequestHandler extends RequestHandler<AuthenticationRequest> {

    @Override
    public RequestResponse handle(AuthenticationRequest message) {

        String username = message.username;
        String password = message.password;

        long userId = LoginAuthenticator.authenticate(username, password);
        if(userId <= 0) {
            //Auth failed
            return new AuthenticationResponse("", false);
        }

        User user = UserGrabber.get(userId);
        if(null == user) {
            //Something is wrong, investigate
            return new AuthenticationResponse("", false);
        }

        String newAuthKey = AuthenticatedUsers.generateAuthKey();
        AuthenticatedUsers.getInstance().add(newAuthKey, user);

        //send json with auth key
        return new AuthenticationResponse(newAuthKey, true);
    }

}
