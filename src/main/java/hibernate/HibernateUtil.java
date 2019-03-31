package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

/**
 * This class allows access to the <a href="http://hibernate.org/orm/">Hibernate</a> library and contains
 * helper functions to perform SQL operations.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public final class HibernateUtil {
    /**
     * @see {@link org.hibernate.SessionFactory}
     */
    private final static SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Builds a {@link org.hibernate.SessionFactory} from the standard {@code hibernate.cfg.xml}
     *
     * @return The {@link org.hibernate.SessionFactory} object
     */
    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    /**
     * Allows you to execute SQL queries to the configured database.
     *
     * @param sql    The SQL query
     * @param params Named parameters that are used by the SQL query
     * @return The data/results from executing the query. This can be an empty/null list for queries that do not fetch data.
     */
    public static List<?> queryDatabase(String sql, Map<String, Object> params) {
        final Session session = sessionFactory.isClosed() ? sessionFactory.openSession() : sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            final Query query = session.createQuery(sql);
            if (params != null) {
                params.forEach(query::setParameter);
            }
            return query.list();
        } finally {
            session.getTransaction().commit();
        }
    }

    /**
     * Saves a mapped object to the database.
     * Objects must be mapped in the {@code hibernate.cfg.xml} otherwise save will fail.
     *
     * @param obj The mapped Object to persist
     */
    public static void save(Object obj) {
        final Session session = sessionFactory.isClosed() ? sessionFactory.openSession() : sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            session.save(obj);
        } finally {
            session.getTransaction().commit();
        }
    }

    /**
     * Opens a connection to the database
     */
    public static void open() {
        final Session session = sessionFactory.isClosed() ? sessionFactory.openSession() : sessionFactory.getCurrentSession();
        session.beginTransaction();
    }
}