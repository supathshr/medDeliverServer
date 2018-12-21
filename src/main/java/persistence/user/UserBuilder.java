package persistence.user;

public class UserBuilder {
    private User user = new User();

    public UserBuilder setId(long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    public UserBuilder setDob(String dob) {
        user.setDob(dob);
        return this;
    }

    public UserBuilder setGender(String gender) {
        if(!(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f"))) {
            throw new IllegalArgumentException("Cannot set gender, must be M or F - attempted: " + gender);
        }
        user.setGender(gender);
        return this;
    }

    public User build() {
        return user;
    }

}
