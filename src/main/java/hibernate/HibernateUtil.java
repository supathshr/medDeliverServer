package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    public static void save(Object obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            session.save(obj);

        } finally {
            session.getTransaction().commit();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}