package net;

import java.util.HashMap;
import java.util.Map;

public class Codec {
    public static final Map<Class<? extends Request>, RequestEncoder<?>> encoders = new HashMap<>();
    public static final Map<RequestType, RequestHandler<?>> handlers = new HashMap<>();
    public static final Map<RequestType, RequestDecoder<?>> decoders = new HashMap<>();

    static {
        handlers.put(RequestType.AUTHENTICATION, new AuthenticationRequestHandler());
        decoders.put(RequestType.AUTHENTICATION, new AuthenticationRequestDecoder());
        encoders.put(AuthenticationResponse.class, new AuthenticationRequestEncoder());
    }
}
