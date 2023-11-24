package model;

import java.awt.Color;

import org.json.JSONObject;
import persistence.Writable;

// represents a helper with number of seconds required to produce a cookie and cost
public class Helper implements Writable {
    public static final Color COLOR = new Color(211, 211, 211);
    public static final int SIZE_X = 50;
    public static final int SIZE_Y = 50;
    private int secondsPerCookie;
    private int cost;


    // EFFECTS: sets number of seconds required to produce a cookie and cost both to 5
    public Helper() {
        this.secondsPerCookie = 5;
        this.cost = 5;
    }

    public int getSecondsPerCookie() {
        return this.secondsPerCookie;
    }

    public int getCost() {
        return this.cost;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("secondsPerCookie", secondsPerCookie);
        json.put("cost", cost);
        return json;
    }
}