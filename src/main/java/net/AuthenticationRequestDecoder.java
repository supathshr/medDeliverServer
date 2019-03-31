package net;

/**
 * Decodes a Authentication request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class AuthenticationRequestDecoder extends RequestDecoder<AuthenticationRequest> {

    @Override
    public AuthenticationRequest decode(String json) {
        final String username = getString(json, "username");
        final String password = getString(json, "password");
        return new AuthenticationRequest(username, password);
    }
}
