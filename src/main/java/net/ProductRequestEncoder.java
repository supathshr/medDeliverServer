package net;

/**
 * Creates a response to a product request
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class ProductRequestEncoder extends RequestEncoder<ProductRequestResponse> {

    @Override
    public Packet encode(ProductRequestResponse message) {
        final PacketBuilder builder = new PacketBuilder();

        builder.put("id", message.product.getId());
        builder.put("name", message.product.getName());
        builder.put("description", message.product.getDescription());
        builder.put("price", message.product.getPrice());
        builder.put("imageURL", message.product.getImageURL());

        return builder.toPacket();
    }
}
