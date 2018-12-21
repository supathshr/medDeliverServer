package persistence.products;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductGrabber {

    public static Product[] getProducts(long pharmacyId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("from Product WHERE id=:id");
            query.setParameter("id", pharmacyId);
            List<Product> products = query.list();
            return products.toArray(new Product[products.size()]);
        } finally {
            //     session.getTransaction().commit(); //TODO: check if this needed/correct
        }
    }

    public static Product get(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Product product = null;

        try {

            Query query = session.createQuery("from Product WHERE id=:id");
            query.setParameter("id", id);

            Iterator<Product> it = query.iterate();
            int counter = 0;
            while(it.hasNext()) {
                product = it.next();
                counter++;
            }
            if(counter > 0) {
                System.out.println("Found " + counter + " products for id " + id);
            } else {
                System.out.println("Product id " + id + " not found.");
            }
        } finally {
            //     session.getTransaction().commit(); //TODO: check if this needed/correct
        }

        return product;
    }

}
