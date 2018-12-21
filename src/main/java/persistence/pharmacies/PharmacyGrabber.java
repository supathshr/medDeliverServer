package persistence.pharmacies;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Iterator;

public class PharmacyGrabber {

    public static Pharmacy get(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Pharmacy pharmacy = null;

        try {

            Query query = session.createQuery("from Pharmacy WHERE id=:id");
            query.setParameter("id", id);

            Iterator<Pharmacy> it = query.iterate();
            int counter = 0;
            while(it.hasNext()) {
                pharmacy = it.next();
                counter++;
            }
            if(counter > 0) {
                System.out.println("Found " + counter + " pharmacies for id " + id);
            } else {
                System.out.println("Pharmacy id " + id + " not found.");
            }
        } finally {
            //     session.getTransaction().commit(); //TODO: check if this needed/correct
        }

        return pharmacy;
    }

}
