package net;

public class AuthenticationResponse extends RequestResponse {
    public final String authKey;
    public final boolean authenticated;

    public AuthenticationResponse(String authKey, boolean authenticated) {
        super(RequestType.AUTHENTICATION);
        this.authKey = authKey;
        this.authenticated = authenticated;
    }

}
