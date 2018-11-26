package net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import utils.JsonSearch;

public abstract class RequestDecoder<T extends Request> {

    public abstract T decode(String json);

    /**
     * Helper function to get a String value from a json by it's key
     */
    protected String getString(String json, String name) {

        JsonObject obj = new Gson().fromJson(json, JsonObject.class);

        if (obj.has(name)) {
            return obj.get(name).getAsString();
        }

        throw new IllegalArgumentException("Cannot find " + name + " in json: " + json);
    }

    /**
     * Helper function to get an Integer value from a json by it's key
     */
    protected int getInt(String json, String name) {

        JsonObject obj = new Gson().fromJson(json, JsonObject.class);

        if (obj.has(name)) {
            return obj.get(name).getAsInt();
        }

        throw new IllegalArgumentException("Cannot find " + name + " in json: " + json);
    }

    /**
     * Helper function to get an Integer value from a json by it's key
     */
    protected long getLong(String json, String name) {

        JsonObject obj = new Gson().fromJson(json, JsonObject.class);

        if (obj.has(name)) {
            return obj.get(name).getAsLong();
        }

        throw new IllegalArgumentException("Cannot find " + name + " in json: " + json);
    }

    /**
     * Helper function to get a Boolean value from a json by it's key
     */
    protected boolean getBoolean(String json, String name) {

        return Boolean.parseBoolean(getString(json, name));

    }
}
