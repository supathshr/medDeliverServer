package net;

/**
 * Handles a decoded Request
 *
 * @param <T> The Request received
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public abstract class RequestHandler<T extends Request> {
    /**
     * Handles a received Request
     *
     * @param message The Request received
     * @return The response to the Request
     */
    public abstract RequestResponse handle(T message);
}