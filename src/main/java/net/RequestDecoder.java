package net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Decodes JSON messages received from the client.
 *
 * @param <T> The Request to contain the decoded message.
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
public abstract class RequestDecoder<T extends Request> {

    /**
     * Decodes the JSON and creates a Request object.
     *
     * @param json The JSON String to decode
     * @return The decoded Request object
     */
    public abstract T decode(String json);

    /* Note: helper functions below are to aid decoding json */

    /**
     * Helper function to get a String value from a json by it's key
     *
     * @param json The JSON string to search in
     * @param name The key name
     * @return The value found from the key in json
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
     *
     * @param json The JSON string to search in
     * @param name The key name
     * @return The value found from the key in json
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
     *
     * @param json The JSON string to search in
     * @param name The key name
     * @return The value found from the key in json
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
     *
     * @param json The JSON string to search in
     * @param name The key name
     * @return The value found from the key in json
     */
    protected boolean getBoolean(String json, String name) {

        return Boolean.parseBoolean(getString(json, name));

    }
}
