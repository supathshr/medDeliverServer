package persistence;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Iterator;

public class UserFactory {
    public static User getUser(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = null;
        try {

            Query query = session.createQuery("from User WHERE id=:id");
            query.setParameter("id", id);

            Iterator<User> it = query.iterate();
            int counter = 0;
            while(it.hasNext()) {
                user = it.next();
                counter++;
            }
            if(counter > 0) {
                System.out.println("Found " + counter + " users for id " + id);
            } else {
                System.out.println("User id " + id + " not found.");
            }
        } finally {
       //     session.getTransaction().commit(); //TODO: check if this needed/correct
        }
        return user;
    }
}
