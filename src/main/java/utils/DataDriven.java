package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DataDriven {
    private static final String JSON_PATH = "testData/testData.json";


    public static JsonObject jsonReader() {
        try (Reader reader = new FileReader(JSON_PATH)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Could not read JSON file at: " + JSON_PATH, e);
        }
    }

    public static String getValue(String object, String key) {
        return jsonReader().getAsJsonObject(object).get(key).getAsString();
    }

    public static Object[][] getValidUsers() {
        JsonArray users = jsonReader().getAsJsonArray("validUsers");
        Object[][] result = new Object[users.size()][2];
        for (int i = 0; i < users.size(); i++) {
            JsonObject u = users.get(i).getAsJsonObject();
            result[i][0] = u.get("username").getAsString();
            result[i][1] = u.get("password").getAsString();
        }
        return result;
    }

}
