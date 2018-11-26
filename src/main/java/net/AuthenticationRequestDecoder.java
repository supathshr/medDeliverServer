package net;

public class AuthenticationRequestDecoder extends RequestDecoder<AuthenticationRequest> {

    @Override
    public AuthenticationRequest decode(String json) {

        String username = getString(json, "username");
        String password = getString(json, "password");

        return new AuthenticationRequest(username, password);
    }

}
