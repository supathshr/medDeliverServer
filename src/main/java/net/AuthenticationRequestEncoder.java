package net;

public class AuthenticationRequestEncoder extends RequestEncoder<AuthenticationResponse> {

    @Override
    public Packet encode(AuthenticationResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        builder.put("authKey", message.authKey);
        builder.put("authenticated", message.authenticated);

        return builder.toPacket();

    }

}
