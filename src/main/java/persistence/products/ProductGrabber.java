package persistence.products;

import hibernate.HibernateUtil;

import java.util.HashMap;
import java.util.List;

/**
 * This class is used to grab Product information from the database.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductGrabber {

    /**
     * Gets and returns an array of products that are linked to a specific pharmacy.
     *
     * @param pharmacyId The pharmacy Id
     * @return An array of products that is with a specific pharmacy otherwise null if no products found.
     */
    public static Product[] getProducts(long pharmacyId) {
        final List<?> result = HibernateUtil.queryDatabase("from Product WHERE pharmacyID=:id", new HashMap<String, Object>() {{
            put("id", pharmacyId);
        }});


        if (result.size() <= 0) {
            System.out.println("No products for pharmacy id " + pharmacyId + " found.");
            return null;
        }

        return result.toArray(new Product[0]);
    }

    /**
     * Gets a product by id from a specific pharmacy
     *
     * @param id         The product Id
     * @param pharmacyId The pharmacy to get the product from
     * @return The Product object of the found product otherwise null
     */
    public static Product get(long id, int pharmacyId) {
        final List<?> result = HibernateUtil.queryDatabase("from Product WHERE id=:id and pharmacyID=:pharmacyId", new HashMap<String, Object>() {{
            put("id", id);
            put("pharmacyId", pharmacyId);
        }});

        if (result.size() <= 0) {
            System.out.println("No products for pharmacy id " + pharmacyId + " found.");
            return null;
        }

        return (Product) result.get(0);
    }
}
