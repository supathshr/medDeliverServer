package persistence.jobs;

import hibernate.HibernateUtil;

import java.util.HashMap;
import java.util.List;

/**
 * This class is used to grab Jobs information from the database.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class JobGrabber {

    /**
     * Gets a specific Job based on the Id
     *
     * @param id The Job ID
     * @return The Job object if a matching id is found
     */
    public static Job get(long id) {
        final List<?> result = HibernateUtil.queryDatabase("from Job WHERE jobId=:id", new HashMap<String, Object>() {{
            put("id", id);
        }});

        if (result.size() <= 0) {
            System.out.println("No job for Id " + id + " found.");
            return null;
        }

        return (Job) result.get(0);
    }
}
