package net;

/**
 * Decodes a registration request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class RegisterRequestDecoder extends RequestDecoder<RegisterRequest> {

    @Override
    public RegisterRequest decode(String json) {
        final String username = getString(json, "username");
        final String password = getString(json, "password");
        final String firstName = getString(json, "firstName");
        final String lastName = getString(json, "lastName");
        final String dob = getString(json, "dob");
        final String gender = getString(json, "gender");
        return new RegisterRequest(username, password, firstName, lastName, dob, gender);
    }
}
