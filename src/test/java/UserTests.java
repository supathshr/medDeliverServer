import hibernate.HibernateUtil;
import org.junit.Test;
import persistence.logins.LoginAuthenticator;
import persistence.user.User;
import persistence.user.UserBuilder;
import persistence.user.UserGrabber;

import static junit.framework.TestCase.*;

public class UserTests {

    @Test
    public void testSaveUser() {
        final int testUserId = 445;

        User user = new UserBuilder()
                .setFirstName("Suraj")
                .setLastName("Kumar")
                .setDob("1996-02-03")
                .setGender("M")
                .setId(testUserId)
                .build();

        HibernateUtil.save(user);

        User u = UserGrabber.get(testUserId);

        assertNotNull(u);
        assertEquals("Suraj", u.getFirstName());
        assertEquals("Kumar", u.getLastName());
        assertEquals("1996-02-03", u.getDob());
        assertEquals("M", u.getGender());
    }


    @Test
    public void testGetUser() {
        User user = UserGrabber.get(445);
        assertNotNull(user);
        assertEquals("Suraj", user.getFirstName());
        assertEquals("Kumar", user.getLastName());
    }

    @Test
    public void testAuthenticationWithCorrectCreds() {
        long userId = LoginAuthenticator.authenticate("test", "password");
        assertTrue(userId > 0);
    }

    @Test
    public void testAuthenticationWithIncorrectCreds() {
        long userId = LoginAuthenticator.authenticate("test", "wrongPassword");
        long userId2 = LoginAuthenticator.authenticate("noSuchUser", "wrongPassword");
        assertTrue(userId <= 0);
        assertTrue(userId2 <= 0);
    }
}

