package persistence;

import model.Bakery;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Citations:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads bakery from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads bakery from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Bakery read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBakery(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bakery from JSON object and returns it
    private Bakery parseBakery(JSONObject jsonObject) {
        int numCookies = jsonObject.getInt("numCookies");
        Bakery bakery = new Bakery();
        bakery.setNumCookies(numCookies);
        addHelpers(bakery, jsonObject);
        return bakery;
    }

    // MODIFIES: bakery
    // EFFECTS: parses helpers from JSON object and adds them to bakery
    private void addHelpers(Bakery bakery, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("helpers");
        for (Object json : jsonArray) {
            bakery.addHelper();
        }
    }
}