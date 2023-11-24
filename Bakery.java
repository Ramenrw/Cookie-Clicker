package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

// represents a bakery with number of cookies and list of helpers
public class Bakery implements Writable {
    private int numCookies;
    private List<Helper> helpers;

    // EFFECTS: sets number of cookies as 0 and an empty list of helpers
    public Bakery() {
        this.numCookies = 0;
        helpers = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds 1 cookie
    public void addCookie() {
        this.numCookies++;
    }

    // MODIFIES: this
    // EFFECTS: subtracts 5 cookies and add a helper to helpers if number of cookies is greater or equal to 5
    public void buyHelper() {
        if (this.numCookies >= 5) {
            this.numCookies -= 5;
            addHelper();
        }
    }

    public void addHelper() {
        helpers.add(new Helper());
    }

    public void setNumCookies(int numCookies) {
        this.numCookies = numCookies;
    }

    public int getNumCookies() {
        return this.numCookies;
    }

    public List<Helper> getHelpers() {
        return this.helpers;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numCookies", numCookies);
        json.put("helpers", helpersToJson());
        return json;
    }

    // EFFECTS: returns things in this bakery as a JSON array
    private JSONArray helpersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Helper helper : helpers) {
            jsonArray.put(helper.toJson());
        }

        return jsonArray;
    }
}