import hibernate.HibernateUtil;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import net.*;
import utils.JsonSearch;

import java.util.HashSet;
import java.util.Set;

public final class Main {
    public static void main(String[] args) {
        HibernateUtil.open();

        final VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setMaxEventLoopExecuteTime(10000000000L);

        final Vertx vertx = Vertx.vertx(vertxOptions);
        final HttpServer server = vertx.createHttpServer();
        final Router router = Router.router(vertx);
        final Route route = router.route()
                .method(HttpMethod.POST)
                .method(HttpMethod.GET);

        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("x-requested-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");
        allowedHeaders.add("X-PINGARUNER");

        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);

        allowedMethods.add(HttpMethod.DELETE);
        allowedMethods.add(HttpMethod.PATCH);
        allowedMethods.add(HttpMethod.PUT);

        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));

        route.produces("application/json").handler(ctx -> {

            System.out.println("Received HTTP request");

            final HttpServerResponse response = ctx.response();
            final HttpServerRequest request = ctx.request();

            request.bodyHandler(body -> {
                final String json = body.toString();
                System.out.println("Received: " + json);

                final String receivedRequestType = JsonSearch.get(json, "request").toLowerCase().trim();

                if(null == receivedRequestType || receivedRequestType.length() <= 0) {
                    System.err.println("No request in json found.");
                }

                RequestType requestType = null;

                for (RequestType type : RequestType.values()) {

                    if (type.toString().equalsIgnoreCase(receivedRequestType)) {
                        requestType = type;
                        break;
                    }

                }

                if (null == requestType) {
                    System.err.println("No suitable request type found for: " + requestType);
                    return;
                }

                System.out.println("Received type: " + requestType);

                //TODO: Uncomment as this validates authKey

//                String authKey = JsonSearch.get(json, "authKey").toLowerCase().trim();
//                boolean authKeyPresent = (null != authKey && authKey.length() > 0);

              //  if(!authKeyPresent && requestType != RequestType.AUTHENTICATION) {
              //      System.out.println("No auth key present for " + receivedRequestType + " request.");
              //      return;
              //  }

               // if(null == AuthenticatedUsers.getInstance().get(authKey)) {
               //     System.err.println("Received authKey '" + authKey + "' but that is not a registered authKey!");
               //     return;
               // }

                final RequestDecoder decoder = Codec.decoders.get(requestType);

                if (null == decoder) {
                    System.err.println("No decoder found for type: " + requestType.toString());
                    return;
                }

                final RequestHandler handler = Codec.handlers.get(requestType);

                if (null == handler) {
                    System.err.println("No handler found for type: " + requestType.toString());
                    return;
                }

                final Request decodedMessage = decoder.decode(json);

                if (null == decodedMessage) {
                    System.err.println("Failed to decode json: " + json);
                    return;
                }

                final RequestResponse message = handler.handle(decodedMessage);

                if (null == message) {
                    return;
                }

                final RequestEncoder encoder = Codec.encoders.get(message.getClass());

                if (null == encoder) {
                    System.err.println("Failed to encode " + message.getClass());
                    return;
                }

                final String encodedJson = encoder.encode(message).data;
                response.setStatusCode(200);
                response.putHeader("Access-Control-Allow-Origin", "*");
                response.putHeader("Content-Length", String.valueOf(encodedJson.length()));
                response.write(encodedJson);
                response.end();

                System.out.println("Sent; " + encodedJson);
            });

        });

        System.out.println("HTTP server is online.");
        server.requestHandler(router::accept).listen(8080);
    }

}
