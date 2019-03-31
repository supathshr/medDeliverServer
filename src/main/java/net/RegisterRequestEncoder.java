package net;

/**
 * Creates a response to a registration request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class RegisterRequestEncoder extends RequestEncoder<RegisterResponse> {

    @Override
    public Packet encode(RegisterResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        builder.put("registered", message.registered);
        builder.put("reason", message.reason);

        return builder.toPacket();
    }
}
