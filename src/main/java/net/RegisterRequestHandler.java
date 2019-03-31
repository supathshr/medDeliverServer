package net;

import hibernate.HibernateUtil;
import persistence.user.User;
import persistence.user.UserBuilder;
import persistence.user.UserGrabber;

/**
 * Handles a registration request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class RegisterRequestHandler extends RequestHandler<RegisterRequest> {

    @Override
    public RequestResponse handle(RegisterRequest message) {
        final String username = message.username;
        final String password = message.password;
        final String firstName = message.firstName;
        final String lastName = message.lastName;
        final String dob = message.dob;
        final String gender = message.gender;

        boolean userExists = UserGrabber.exists(username);
        if (userExists) {
            return new RegisterResponse(false, "The username is already taken.");
        }

        String reason;
        boolean registered;

        try {
            final User newUser = new UserBuilder()
                    .setUserType("customer")
                    .setPassword(password)
                    .setUsername(username)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setDob(dob)
                    .setGender(gender)
                    .build();
            HibernateUtil.save(newUser);
            registered = true;
            reason = "Successfully registered " + username;
        } catch (Exception e) {
            registered = false;
            reason = "An internal error occurred on the server while registering.";
            e.printStackTrace();
        }

        return new RegisterResponse(registered, reason);
    }
}
