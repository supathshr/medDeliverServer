package net;

/**
 * The response to a registration request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class RegisterResponse extends RequestResponse {
    public final boolean registered;
    public final String reason;

    public RegisterResponse(boolean registered, String reason) {
        super(RequestType.REGISTER);
        this.registered = registered;
        this.reason = reason;
    }
}
