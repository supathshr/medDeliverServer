package persistence.pharmacies;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to grab Pharmacy information from the database.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class PharmacyGrabber {

    public static <T> ArrayList<T> removeDuplicates(List<T> list)
    {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }

    /**
     * Gets an array of pharmacies were the post code or address match the passed query.
     *
     * @param search The query to search for pharmacies with
     * @return An array of Pharmacy objects that have been found otherwise null for no pharmacies matching the search.
     */
    public static Pharmacy[] get(String search) {
        //final List<?> result = HibernateUtil.queryDatabase("from Pharmacy WHERE postCode LIKE '%" + search + "%' OR address LIKE '%" + search + "%'", null);

        final List<?> result1 = HibernateUtil.queryDatabase("from Pharmacy WHERE postCode LIKE :search OR name LIKE :search OR address LIKE :search", new HashMap<String, Object>() {{
            put("search", "%" + search + "%");
        }});

        final List<?> result = removeDuplicates(result1);

        if (result.size() <= 0) {
            System.out.println("No pharmacies found for query: " + search);
            return null;
        }

        return result.toArray(new Pharmacy[result.size()]);
    }

    /**
     * Gets the Pharmacy object by a specific pharmacyID
     *
     * @param id The pharmacy Id
     * @return The Pharmacy object, null if no matching id found.
     */
    public static Pharmacy get(long id) {
        final List<?> result = HibernateUtil.queryDatabase("from Pharmacy WHERE pharmacyID=:id", new HashMap<String, Object>() {{
            put("id", id);
        }});

        if (result.size() <= 0) {
            System.out.println("No pharmacy for id " + id + " found.");
            return null;
        }

        return (Pharmacy) result.get(0);
    }

}
