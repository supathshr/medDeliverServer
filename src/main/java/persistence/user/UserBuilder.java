package persistence.user;

/**
 * This class is used to create a User object. It should be
 * used when manually creating a User object.
 * <p>
 * The <a href="https://en.wikipedia.org/wiki/Method_chaining">Method Chaining Pattern</a> is used
 * so to build a {@link persistence.user.User}, can be done like so:
 * {@code final User user = UserBuilder
 * .setId(1)
 * .setFirstName("John")
 * .setLastName("Doe")
 * .setDob("1996-02-03")
 * .setUser("testUsername")
 * .setUserType("customer")
 * .setPassword("testPassword")
 * .setGender("M")
 * .build(); }
 * <p>
 * Note: {@link persistence.user.User}s created are not persistent and will need to be saved
 * via the {@link hibernate.HibernateUtil#save(Object)} method.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class UserBuilder {
    private final User user = new User();

    /**
     * Sets the id of the user.
     *
     * @param id The Id to set.
     * @return The {@link UserBuilder} object
     */
    public UserBuilder setId(long id) {
        user.setId(id);
        return this;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name of the user to set.
     * @return The {@link UserBuilder} object
     */
    public UserBuilder setFirstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name to set.
     * @return The {@link UserBuilder} object
     */
    public UserBuilder setLastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    /**
     * Sets the date of birth of the user.
     * <p>
     * DOB format <strong>must</strong> be like so: yyyy-mm-dd otherwise
     * when persisting the user, it will fail. This format is expected by
     * the database.
     *
     * @param dob The dob to set
     * @return The {@link UserBuilder} object
     */
    public UserBuilder setDob(String dob) {
        user.setDob(dob);
        return this;
    }

    /**
     * Sets the username of the User. This is the name
     * users will use to login to Med-Deliver app.
     *
     * @param username The username to set
     * @return The {@link UserBuilder} object
     */
    public UserBuilder setUsername(String username) {
        user.setUsername(username);
        return this;
    }

    /**
     * Sets the users type.
     * <p>
     * The available types are:
     * <ul>
     * <li>customer</li>
     * <li>driver</li>
     * </ul>
     * <p>
     * If the type does not match the above, an IllegalArgumentException is thrown.
     *
     * @param userType The user type
     * @return The {@link UserBuilder} object
     */
    public UserBuilder setUserType(String userType) {
        if (!(userType.equalsIgnoreCase("customer") || userType.equalsIgnoreCase("driver"))) {
            throw new IllegalArgumentException("Cannot set userType, must be 'customer' or 'driver' - attempted: " + userType);
        }
        user.setUserType(userType);
        return this;
    }

    /**
     * The users encrypted password used for authentication.
     *
     * @param password The encrypted password
     * @return The {@link UserBuilder} object
     */
    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    /**
     * The gender of the user.
     * This must be either 'M' or 'F' without quotes.
     *
     * @param gender The users gender.
     * @return The {@link UserBuilder} object
     */
    public UserBuilder setGender(String gender) {
        if (!(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f"))) {
            throw new IllegalArgumentException("Cannot set gender, must be M or F - attempted: " + gender);
        }
        user.setGender(gender);
        return this;
    }

    /**
     * Gets a {@link persistence.user.User} object based on the data provided.
     *
     * @return The {@link persistence.user.User} object that is created by the builder.
     */
    public User build() {
        return user;
    }
}