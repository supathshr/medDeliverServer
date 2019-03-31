package net;

/**
 * The response to an authentication request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class AuthenticationResponse extends RequestResponse {
    public final String authKey;
    public final boolean authenticated;

    public AuthenticationResponse(String authKey, boolean authenticated) {
        super(RequestType.AUTHENTICATION);
        this.authKey = authKey;
        this.authenticated = authenticated;
    }
}
