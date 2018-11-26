package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userlogins")
public class UserCreds {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "userId", updatable = false)
    private long userId;

    @Column(name = "username", updatable = false)
    private String username;

    @Column(name = "password", updatable = true)
    private String password;

    public UserCreds() {}

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUserId(long userId) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
