import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.HibernateUtil;
import io.netty.handler.codec.http.HttpResponseStatus;
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
import persistence.AuthenticatedUsers;

import java.util.HashSet;
import java.util.Set;

/**
 * The entry point to the Med-Deliver server.
 * <p>
 * This class starts up the HTTP server and handles incoming requests and outgoing
 * responses.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public final class Main {
    /**
     * The port the server will be listening on.
     * The port is set as a system property and if not set, defaults to port 8080.
     */
    private static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));

    /**
     * Handles starting all services required for the server.
     * The HTTP server is created and handlers are set here.
     *
     * @param args None required.
     *             Optional: enableTestMode - to run the server in a test mode.
     */
    public static void main(String[] args) {
        /* Open a connection to the database */
        HibernateUtil.open();

        /* Check if we should enable test mode or not */
        if(args.length > 0) {
            String arg = args[0].toLowerCase().trim();
            if(arg.equalsIgnoreCase("enableTestMode")) {
                /* Add a test auth key so we don't need to authenticate through login */
                AuthenticatedUsers.getInstance().add("1234", null);
                System.out.println("Test mode enabled.");
            }
        }

        /* Setup Vertx and the HTTP server */
        final Vertx vertx = Vertx.vertx(new VertxOptions().setMaxEventLoopExecuteTime(10000000000L));
        final HttpServer server = vertx.createHttpServer();
        final Router router = Router.router(vertx);
        final Route route = router.route()
                .method(HttpMethod.POST)
                .method(HttpMethod.GET);

        final Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("x-requested-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");
        allowedHeaders.add("X-PINGARUNER");

        /* Sets the allowed HTTP requests */
        final Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);

        /* Handles CORS */
        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));

        /* Handles HTTP/JSON messages and responses */
        route.produces("application/json").handler(ctx -> {

            System.out.println("Received HTTP request");

            final HttpServerResponse response = ctx.response();
            final HttpServerRequest request = ctx.request();

            request.bodyHandler(body -> {
                final String json = body.toString();
                System.out.println("JSON IS: " + json);

                final JsonObject obj = new Gson().fromJson(json, JsonObject.class);

                final String authKey = obj.has("authKey")
                        ? obj.get("authKey").getAsString().toLowerCase().trim()
                        : null;

                final String receivedRequestType = obj.has("requestType")
                        ? obj.get("requestType").getAsString().toLowerCase().trim()
                        : null;

                System.out.println("Received: " + json);

                /* Check if we receive a valid request */
                if (null == receivedRequestType || receivedRequestType.length() <= 0) {
                    System.err.println("No request in json found.");
                    response.setStatusCode(HttpResponseStatus.BAD_REQUEST.code());
                    response.end();
                    return;
                }

                RequestType requestType = null;

                for (RequestType type : RequestType.values()) {
                    if (type.toString().equalsIgnoreCase(receivedRequestType)) {
                        requestType = type;
                        break;
                    }
                }

                if (null == requestType) {
                    System.err.println("No suitable request type found for: " + receivedRequestType);
                    response.setStatusCode(HttpResponseStatus.BAD_REQUEST.code());
                    response.end();
                    return;
                }

                System.out.println("Received type: " + requestType);

                boolean authKeyPresent = (null != authKey && authKey.length() > 0);

                /* Checks if there is an authentication key present */
                if (!authKeyPresent && !(requestType == RequestType.AUTHENTICATION || requestType == RequestType.REGISTER)) {
                    System.out.println("No auth key present for " + receivedRequestType + " request.");
                    response.setStatusCode(HttpResponseStatus.FORBIDDEN.code());
                    response.end();
                    return;
                }

                /* Validates authentication key */
                if (!(requestType == RequestType.AUTHENTICATION || requestType == RequestType.REGISTER)) {
                    if (!AuthenticatedUsers.getInstance().exists(authKey)) {
                        response.setStatusCode(HttpResponseStatus.FORBIDDEN.code());
                        response.end();
                        System.err.println("Received authKey '" + authKey + "' but that is not a registered authKey!");
                        return;
                    }
                }

                /* Decode the request for handling */
                final RequestDecoder decoder = Codec.decoders.get(requestType);

                if (null == decoder) {
                    System.err.println("No decoder found for type: " + requestType.toString());
                    response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
                    response.end();
                    return;
                }

                final Request decodedMessage = decoder.decode(json);

                if (null == decodedMessage) {
                    System.err.println("Failed to decode json: " + json);
                    response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
                    response.end();
                    return;
                }

                /* Handles the decoded request */
                final RequestHandler handler = Codec.handlers.get(requestType);

                if (null == handler) {
                    System.err.println("No handler found for type: " + requestType.toString());
                    response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
                    response.end();
                    return;
                }

                /* Handles sending back a response */
                final RequestResponse message = handler.handle(decodedMessage);

                if (null == message) {
                    System.err.println("Nothing to send back to client.");
                    response.setStatusCode(HttpResponseStatus.OK.code());
                    response.end();
                    return;
                }

                final RequestEncoder encoder = Codec.encoders.get(message.getClass());

                if (null == encoder) {
                    System.err.println("Failed to encode " + message.getClass());
                    response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
                    response.end();
                    return;
                }

                final String encodedJson = encoder.encode(message).payload;
                response.setStatusCode(200);
                response.putHeader("Access-Control-Allow-Origin", "*");
                response.putHeader("Content-Length", String.valueOf(encodedJson.length()));
                response.write(encodedJson);
                response.end();

                System.out.println("Sent; " + encodedJson);
            });
        });

        System.out.println("HTTP server is online.");
        server.requestHandler(router::accept).listen(PORT);
    }
}