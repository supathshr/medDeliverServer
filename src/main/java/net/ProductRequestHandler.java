package net;

import persistence.products.ProductGrabber;

/**
 * Handles a product request.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductRequestHandler extends RequestHandler<ProductRequest> {

    @Override
    public RequestResponse handle(ProductRequest message) {
        return new ProductRequestResponse(ProductGrabber.get(message.productId, message.pharmacyId));
    }
}
