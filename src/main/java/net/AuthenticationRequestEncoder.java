package net;

/**
 * Creates a response to an authentication request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class AuthenticationRequestEncoder extends RequestEncoder<AuthenticationResponse> {

    @Override
    public Packet encode(AuthenticationResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        builder.put("authKey", message.authKey);
        builder.put("authenticated", message.authenticated);

        return builder.toPacket();
    }
}
