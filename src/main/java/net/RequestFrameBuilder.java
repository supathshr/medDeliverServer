package net;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RequestFrameBuilder {
    private Map<String, String> out = new HashMap<>();

    public RequestFrameBuilder() {}

    public void put(String key, Object value) {
        out.put(key, String.valueOf(value));
    }

    public Packet toPacket() {
        return new Packet(new Gson().toJson(out));
    }
}
