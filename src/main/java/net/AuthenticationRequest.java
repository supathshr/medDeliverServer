package net;

public class AuthenticationRequest extends Request {
    public final String username;
    public final String password;

    public AuthenticationRequest(String username, String password) {
        super(RequestType.AUTHENTICATION);
        this.username = username;
        this.password = password;
    }
}
