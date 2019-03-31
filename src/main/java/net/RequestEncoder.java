package net;

/**
 * Encodes a Request into a Packet that can be sent back to a client.
 *
 * @param <T> The RequestResponse object containing the data to send back
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public abstract class RequestEncoder<T extends RequestResponse> {
    /**
     * Encodes the RequestResponse to a Packet
     *
     * @param message The RequestResponse to encode
     * @return The Packet to send
     */
    public abstract Packet encode(T message);
}