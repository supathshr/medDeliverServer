package net;

/**
 * This request is to create an account for the Med-Deliver app.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class RegisterRequest extends Request {
    public final String username;
    public final String password;
    public final String firstName;
    public final String lastName;
    public final String dob;
    public final String gender;

    public RegisterRequest(String username, String password, String firstName, String lastName, String dob, String gender) {
        super(RequestType.REGISTER);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
    }
}
