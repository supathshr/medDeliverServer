package net;

public enum RequestType {
    AUTHENTICATION("authentication"),
    GET_PRODUCT("getProduct"),
    GET_PRODUCTS("getProducts");

    private String tag;
    RequestType(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }

}
