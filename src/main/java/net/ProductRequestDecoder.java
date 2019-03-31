package net;

/**
 * Decodes a product request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductRequestDecoder extends RequestDecoder<ProductRequest> {

    @Override
    public ProductRequest decode(String json) {
        return new ProductRequest(getInt(json, "productId"), getInt(json, "pharmacyId"));
    }
}
