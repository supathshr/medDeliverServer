package utils;

public final class JsonSearch {

    public static boolean has(String json, String key) {
        return json.contains("\"" + key + "\":");
    }

    public static String get(final String json, String key) {
        String formattedKey = "\"" + key + "\":";

        int start = json.indexOf(formattedKey);

        if (start < 0) {
            return "-1";
        }

        String value = json.substring(start).replace(formattedKey, "").trim();
        String[] part = value.split("\"");

        return part[1];
    }

    public static String valueFromParent(String json, String parent, String key) {
        return json.substring(json.indexOf("\"" + parent + "\": {"));
    }
}