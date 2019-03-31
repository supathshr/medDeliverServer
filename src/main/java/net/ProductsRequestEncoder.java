package net;

import persistence.products.Product;

/**
 * Creates a response to a products request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductsRequestEncoder extends RequestEncoder<ProductsRequestResponse> {

    @Override
    public Packet encode(ProductsRequestResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        builder.put("pharmacyId", message.pharmacyId);

        final StringBuilder prods = new StringBuilder("[");

        for(int i = 0; i < message.products.length; i++) {
            final Product p = message.products[i];

            prods.append("{");

            prods.append("\"id\":\"").append(p.getId()).append("\",");
            prods.append("\"name\":\"").append(p.getName()).append("\",");
            prods.append("\"description\":\"").append(p.getDescription()).append("\",");
            prods.append("\"price\":\"").append(p.getPrice()).append("\"");

            if(i == (message.products.length - 1)) {
                prods.append("}");
            } else {
                prods.append("},");
            }

        }

        prods.append("]");

        builder.put("products", prods.toString());

        return builder.toPacket();
    }
}
