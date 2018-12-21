package persistence.jobs;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.products.Product;

import java.util.Iterator;

public class JobGrabber {

    /**
     * @param id The order ID
     * @return
     */
    public static Job get(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Job job = null;

        try {

            Query query = session.createQuery("from Job WHERE jobId=:id");
            query.setParameter("id", id);

            Iterator<Job> it = query.iterate();
            int counter = 0;
            while(it.hasNext()) {
                job = it.next();
                counter++;
            }
            if(counter > 0) {
                System.out.println("Found " + counter + " jobs for id " + id);
            } else {
                System.out.println("Cannot find job for order id " + id + " not found.");
            }
        } finally {
            //     session.getTransaction().commit(); //TODO: check if this needed/correct
        }

        return job;
    }
}
