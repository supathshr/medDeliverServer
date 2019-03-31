package net;

/**
 * The base class for all types of requests that the server will handle.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public abstract class Request {
    /**
     * The type of the request
     */
    public final RequestType requestType;

    /**
     * Constructs a new Request
     *
     * @param requestType The request type of this request
     */
    public Request(RequestType requestType) {
        this.requestType = requestType;
    }
}
