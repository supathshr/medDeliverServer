package net;

/**
 * A product request is a request for details of a specific product in a
 * specific pharmacy.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductRequest extends Request {
    public final int productId;
    public final int pharmacyId;

    public ProductRequest(int productId, int pharmacyId) {
        super(RequestType.GET_PRODUCT);
        this.productId = productId;
        this.pharmacyId = pharmacyId;
    }
}
