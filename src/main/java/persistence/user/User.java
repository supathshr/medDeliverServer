package persistence.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "firstName", updatable = true)
    private String firstName;

    @Column(name = "lastName", updatable = true)
    private String lastName;

    @Column(name = "dob", updatable = true)
    private String dob;

    @Column(name = "gender", updatable = true)
    private String gender;

    @Column(name = "userType", updatable = true)
    private String userType;

    public User() { }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "[firstName=" + firstName + "][lastName=" + lastName + "][dob=" + dob + "][gender=" + gender + "]";
    }
}
