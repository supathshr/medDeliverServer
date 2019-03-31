package net;

import persistence.pharmacies.Pharmacy;

/**
 * Creates a response to a pharmacy search request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacySearchRequestEncoder extends RequestEncoder<PharmacySearchRequestResponse> {

    @Override
    public Packet encode(PharmacySearchRequestResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        StringBuilder out = new StringBuilder("[");

        if(message.pharmacies != null) {
            for (int i = 0; i < message.pharmacies.length; i++) {
                final Pharmacy p = message.pharmacies[i];

                out.append("{");
                out.append("\"id\":\"").append(p.getId()).append("\",");
                out.append("\"name\":\"").append(p.getName()).append("\",");
                out.append("\"address\":\"").append(p.getAddress()).append("\",");
                out.append("\"postCode\":\"").append(p.getPostCode()).append("\",");
                out.append("\"rating\":\"").append(p.getRating()).append("\"");

                if (i == (message.pharmacies.length - 1)) {
                    out.append("}");
                } else {
                    out.append("},");
                }
            }
        }

        out.append("]");

        builder.put("pharmacies", out.toString());

        return builder.toPacket();
    }
}
