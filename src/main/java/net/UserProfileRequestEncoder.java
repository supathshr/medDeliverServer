package net;

/**
 * Creates a response to a user profile request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class UserProfileRequestEncoder extends RequestEncoder<UserProfileRequestResponse> {
    @Override
    public Packet encode(UserProfileRequestResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        builder.put("id", message.user.getId());
        builder.put("username", message.user.getUsername());
        builder.put("firstName", message.user.getFirstName());
        builder.put("lastName", message.user.getLastName());
        builder.put("dob", message.user.getDob());
        builder.put("gender", message.user.getGender());
        builder.put("userType", message.user.getUserType());

        return builder.toPacket();
    }
}
