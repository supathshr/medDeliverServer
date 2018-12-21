package persistence.logins;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Iterator;

public class LoginAuthenticator {
    public static long authenticate(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Login user = new Login();
        try {

            Query query = session.createQuery("from Login WHERE username=:username AND password=:password");
            query.setParameter("username", username);
            query.setParameter("password", password);

            Iterator<Login> it = query.iterate();
            int counter = 0;
            while(it.hasNext()) {
                user = it.next();
                counter++;
            }
            if(counter > 0) {
                System.out.println("Found " + counter + " users for username " + username);
            } else {
                System.out.println(username + " not found.");
            }
        } finally {
            session.getTransaction().commit(); //TODO: check if this needed/correct
        }

        return user.getUserId();
    }

}
