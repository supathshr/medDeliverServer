package net;

import persistence.products.ProductGrabber;

/**
 * Handles a products request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductsRequestHandler extends RequestHandler<ProductsRequest> {

    @Override
    public RequestResponse handle(ProductsRequest message) {
        return new ProductsRequestResponse(message.pharmacyId, ProductGrabber.getProducts(message.pharmacyId));
    }
}
