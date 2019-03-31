package net;

/**
 * The different types of requests to handle
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public enum RequestType {
    AUTHENTICATION("authentication"),
    REGISTER("register"),
    GET_PRODUCT("getProduct"),
    GET_PRODUCTS("getProducts"),
    GET_USER("getUser"),
    GET_PHARMACY("getPharmacy"),
    GET_PHARMACIES("getPharmacies");

    /**
     * The identifier for the request found in the JSON
     */
    private final String tag;

    RequestType(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }

}
