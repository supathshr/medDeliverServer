package net;

import persistence.products.Product;

/**
 * The response to a products response
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductsRequestResponse extends RequestResponse {
    public final int pharmacyId;
    public final Product[] products;

    public ProductsRequestResponse(int pharmacyId, Product[] products) {
        super(RequestType.GET_PRODUCTS);
        this.pharmacyId = pharmacyId;
        this.products = products;
    }
}
