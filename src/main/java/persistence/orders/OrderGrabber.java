package persistence.orders;

import hibernate.HibernateUtil;

import java.util.HashMap;
import java.util.List;

/**
 * This class is used to grab Order information from the database.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class OrderGrabber {

    /**
     * Gets a specific Order based on the Id
     *
     * @param id The id of the order
     * @return The Order object, null if no matching Id found
     */
    public static Order get(long id) {
        final List<?> result = HibernateUtil.queryDatabase("from Order WHERE id=:id", new HashMap<String, Object>() {{
            put("id", id);
        }});

        if (result.size() <= 0) {
            System.out.println("No product for id " + id + " found.");
            return null;
        }

        return (Order) result.get(0);
    }

}
