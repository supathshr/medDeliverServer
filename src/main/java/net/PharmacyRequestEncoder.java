package net;

/**
 * Creates a response for a pharmacy request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacyRequestEncoder extends RequestEncoder<PharmacyRequestResponse> {

    @Override
    public Packet encode(PharmacyRequestResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        builder.put("id", message.id);
        builder.put("name", message.name);
        builder.put("address", message.address);
        builder.put("postCode", message.postCode);
        builder.put("rating", message.rating);

        return builder.toPacket();
    }
}
