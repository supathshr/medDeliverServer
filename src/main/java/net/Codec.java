package net;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles routing decoders, handlers and encoders to requests
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public class Codec {
    public static final Map<Class<? extends Request>, RequestEncoder<?>> encoders = new HashMap<>();
    public static final Map<RequestType, RequestHandler<?>> handlers = new HashMap<>();
    public static final Map<RequestType, RequestDecoder<?>> decoders = new HashMap<>();

    /*
     * Sets routes
     */
    static {
        handlers.put(RequestType.AUTHENTICATION, new AuthenticationRequestHandler());
        decoders.put(RequestType.AUTHENTICATION, new AuthenticationRequestDecoder());
        encoders.put(AuthenticationResponse.class, new AuthenticationRequestEncoder());

        handlers.put(RequestType.REGISTER, new RegisterRequestHandler());
        decoders.put(RequestType.REGISTER, new RegisterRequestDecoder());
        encoders.put(RegisterResponse.class, new RegisterRequestEncoder());

        handlers.put(RequestType.GET_PRODUCT, new ProductRequestHandler());
        decoders.put(RequestType.GET_PRODUCT, new ProductRequestDecoder());
        encoders.put(ProductRequestResponse.class, new ProductRequestEncoder());

        handlers.put(RequestType.GET_PRODUCTS, new ProductsRequestHandler());
        decoders.put(RequestType.GET_PRODUCTS, new ProductsRequestDecoder());
        encoders.put(ProductsRequestResponse.class, new ProductsRequestEncoder());

        handlers.put(RequestType.GET_USER, new UserProfileRequestHandler());
        decoders.put(RequestType.GET_USER, new UserProfileRequestDecoder());
        encoders.put(UserProfileRequestResponse.class, new UserProfileRequestEncoder());

        handlers.put(RequestType.GET_PHARMACY, new PharmacyRequestHandler());
        decoders.put(RequestType.GET_PHARMACY, new PharmacyRequestDecoder());
        encoders.put(PharmacyRequestResponse.class, new PharmacyRequestEncoder());

        handlers.put(RequestType.GET_PHARMACIES, new PharmacySearchRequestHandler());
        decoders.put(RequestType.GET_PHARMACIES, new PharmacySearchRequestDecoder());
        encoders.put(PharmacySearchRequestResponse.class, new PharmacySearchRequestEncoder());
    }
}
