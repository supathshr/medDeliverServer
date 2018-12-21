package persistence.orders;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.pharmacies.Pharmacy;

import java.util.Iterator;

public class OrderGrabber {

    public static Order get(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Order order = null;

        try {

            Query query = session.createQuery("from Order WHERE id=:id");
            query.setParameter("id", id);

            Iterator<Order> it = query.iterate();
            int counter = 0;
            while(it.hasNext()) {
                order = it.next();
                counter++;
            }
            if(counter > 0) {
                System.out.println("Found " + counter + " orders for id " + id);
            } else {
                System.out.println("Order id " + id + " not found.");
            }
        } finally {
            //     session.getTransaction().commit(); //TODO: check if this needed/correct
        }

        return order;
    }

}
