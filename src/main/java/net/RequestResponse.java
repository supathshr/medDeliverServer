package net;

/**
 * The response to send back to a Request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public abstract class RequestResponse extends Request {
    /**
     * Constructs a new RequestResponse
     *
     * @param requestType The request type this response is for
     */
    public RequestResponse(RequestType requestType) {
        super(requestType);
    }
}
