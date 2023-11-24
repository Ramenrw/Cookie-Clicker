package ui;

import model.Bakery;
import model.Helper;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;

// Citations:
// https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Cookie clicker application
public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 850;

    private static final String JSON_STORE = "./data/game.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ScheduledExecutorService executor;
    private Bakery bakery;

    // EFFECTS: constructs game and runs application
    public Game() throws FileNotFoundException {
        bakery = new Bakery();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(addCookie, 2, 5, TimeUnit.SECONDS);
    }

    // MODIFIES: this
    // EFFECTS: creates a helper at the cost of 5 cookies
    //          helper adds a cookie every 5 seconds. Is stackable
    public void buyHelper() {
        if (bakery.getNumCookies() < 5) {
            // do nothing
        } else {
            bakery.buyHelper();
        }
    }

    // EFFECTS: saves the bakery to file
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(bakery);
            jsonWriter.close();
            System.out.println("Saved progress to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the bakery from file
    public void load() {
        try {
            bakery = jsonReader.read();
            System.out.println("Loaded progress from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECT: determines how many cookies to add every 5 seconds depending on number of helpers
    Runnable addCookie = new Runnable() {
        public void run() {
            for (Helper helper : bakery.getHelpers()) {
                bakery.addCookie();
            }
        }
    };

    // EFFECTS: returns list of helpers
    public List<Helper> getHelpers() {
        return bakery.getHelpers();
    }

    // MODIFIES: bakery
    // EFFECTS: adds cookie to bakery
    public void addCookie() {
        bakery.addCookie();
    }

    // EFFECTS: returns number of cookies in bakery
    public int getNumCookies() {
        return bakery.getNumCookies();
    }

    public void removeHelper() {
        bakery.removeHelper();
    }
}