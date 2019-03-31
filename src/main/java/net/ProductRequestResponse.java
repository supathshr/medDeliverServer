package net;

import persistence.products.Product;

/**
 * The response to a product request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductRequestResponse extends RequestResponse {
    public final Product product;

    public ProductRequestResponse(Product product) {
        super(RequestType.GET_PRODUCT);
        this.product = product;
    }
}
