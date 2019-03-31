package net;

/**
 * A products request is a request for details on all the products
 * in a specific pharmacy.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductsRequest extends Request {
    public final int pharmacyId;

    public ProductsRequest(int pharmacyId) {
        super(RequestType.GET_PRODUCTS);
        this.pharmacyId = pharmacyId;
    }
}
