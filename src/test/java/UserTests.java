import hibernate.HibernateUtil;
import org.junit.Test;
import persistence.User;
import persistence.UserAuthenticator;
import persistence.UserBuilder;
import persistence.UserFactory;

import static junit.framework.TestCase.*;

public class UserTests {

    @Test
    public void testSaveUser() {
        final int testUserId = 444;

        User user = new UserBuilder()
                .setFirstName("Suraj")
                .setLastName("Kumar")
                .setDob("1996-02-03")
                .setGender("M")
                .setId(testUserId)
                .build();

        HibernateUtil.save(user);

        User u = UserFactory.getUser(testUserId);

        assertNotNull(u);
        assertEquals("Suraj", u.getFirstName());
        assertEquals("Kumar", u.getLastName());
        assertEquals("1996-02-03", u.getDob());
        assertEquals("M", u.getGender());
    }


    @Test
    public void testGetUser() {
       User user = UserFactory.getUser(1);
       assertNotNull(user);
       assertEquals("Suraj", user.getFirstName());
       assertEquals("Kumar", user.getLastName());
    }

    @Test
    public void testAuthenticationWithCorrectCreds() {
        long userId = UserAuthenticator.authenticate("test", "password");
        assertTrue(userId > 0);
    }

    @Test
    public void testAuthenticationWithIncorrectCreds() {
        long userId = UserAuthenticator.authenticate("test", "wrongPassword");
        long userId2 = UserAuthenticator.authenticate("noSuchUser", "wrongPassword");
        assertTrue(userId <= 0);
        assertTrue(userId2 <= 0);
    }
}

