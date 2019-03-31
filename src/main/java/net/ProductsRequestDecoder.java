package net;

/**
 * Decodes a products request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductsRequestDecoder extends RequestDecoder<ProductsRequest> {

    @Override
    public ProductsRequest decode(String json) {
        return new ProductsRequest(getInt(json, "pharmacyId"));
    }
}
