package ui;

import model.Bakery;
import model.Helper;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Citations:
// https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html

// Cookie clicker application
public class Game {
    private static final String JSON_STORE = "./data/game.json";
    private Bakery bakery;
    private Helper helper;
    private ScheduledExecutorService executor;
    private Scanner input;
    private boolean isRunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates empty lists of Bakery and executors
    //          prompts user to create a new Bakery then starts the game
    public Game() throws FileNotFoundException {
        bakery = new Bakery();
        helper = new Helper();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        System.out.println("Welcome to Cookie Clicker!");
        playGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void playGame() {
        startMessage();
        input = new Scanner(System.in);
        isRunning = true;
        while (isRunning) {
            options();
        }
        conclude();
    }

    // MODIFIES: this
    // EFFECTS: creates a cookieHelper at the cost of 5 cookies
    //          cookieHelper adds a cookie every 5 seconds. Is stackable
    public void buyHelper() {
        if (bakery.getNumCookies() < 5) {
            System.out.println("Sorry, you do not have enough cookies to buy a helper.");
        } else {
            bakery.buyHelper();
            if (bakery.getNumCookies() == 1) {
                System.out.println("You have bought a helper! You now have " + bakery.getNumCookies() + " cookie.");
            } else {
                System.out.println("You have bought a helper! You now have " + bakery.getNumCookies() + " cookies.");
            }
            addExecutor();
        }
    }

    // EFFECTS: displays possible actions for user
    public void startMessage() {
        System.out.println("Here are your options:\n\tEnter - add one cookie\n\tq - obtain information about your "
                + "current cookies \n\tw - obtain information about your current helpers \n\ta - purchase a helper "
                + "\n\ti - display general information about helpers\n\ts - save current progress\n\tz - load saved "
                + "progress\n\tx - quit");
    }

    // EFFECTS: displays information about user's bakery
    public void userCookieInformation() {
        System.out.println("You currently have " + bakery.getNumCookies() + " cookies.");
    }

    // EFFECTS: displays information about user's Helpers
    public void userHelperInformation() {
        System.out.println("You currently have " + bakery.getHelpers().size() + " helpers.\nTogether, they will "
                + "produce " + bakery.getHelpers().size() + " cookies every " + helper.getSecondsPerCookie()
                + " seconds.");
    }

    // EFFECTS: displays information about Helpers
    public void generalHelperInformation() {
        System.out.println("Each helper gives you a cookie every " + helper.getSecondsPerCookie() + " seconds"
                + "\nEach helper costs " + helper.getCost() + " cookies to buy.");
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

    // EFFECTS: loads the bakery from file
    public void load() {
        try {
            for (int i = 0; i < bakery.getHelpers().size(); i++) {
                executor.shutdown();
            }
            bakery = jsonReader.read();
            for (int i = 0; i < bakery.getHelpers().size(); i++) {
                addExecutor();
            }
            System.out.println("Loaded progress from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void options() {
        String command = input.nextLine().toLowerCase();
        if (command.isBlank()) {
            bakery.addCookie();
        } else if (command.equals("q")) {
            userCookieInformation();
        } else if (command.equals("w")) {
            userHelperInformation();
        } else if (command.equals("a")) {
            buyHelper();
        } else if (command.equals("i")) {
            generalHelperInformation();
        } else if (command.equals("s")) {
            save();
        } else if (command.equals("z")) {
            load();
        } else if (command.equals("x")) {
            isRunning = false;
        } else {
            System.out.println("Invalid input, please try again.");
        }
    }

    public void addExecutor() {
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(addCookie, 5, 5, TimeUnit.SECONDS);
    }

    Runnable addCookie = new Runnable() {
        public void run() {
            bakery.addCookie();
        }
    };

    // MODIFIES: this
    // EFFECTS: terminates all existing helpers
    //          displays information of bakery
    public void conclude() {
        System.out.println("\nThanks for playing! You ended with " + bakery.getNumCookies() + " cookies and "
                + bakery.getHelpers().size() + " helpers.");
        System.exit(0);
    }
}