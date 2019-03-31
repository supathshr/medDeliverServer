package net;

/**
 * The data we send back over HTTP.
 * As this is a REST based API packets that are sent back are
 * in a JSON format for clients. Therefore a Packets payload
 * must be a String representative of the JSON response.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class Packet {
    /**
     * The data the Packet contains to be sent out.
     * This should always be a valid JSON String.
     */
    public final String payload;

    public Packet(String payload) {
        //The current framework in place doesn't really format the Arrays inside the JSON properly.
        //This fixes that so Arrays are displayed correctly in the JSON String.
        this.payload = payload.replaceAll("\"\\[", "[")
                .replaceAll("]\"", "]")
                .replaceAll("\\\\", "")
                .trim();
    }
}
