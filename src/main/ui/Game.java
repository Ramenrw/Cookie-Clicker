package ui;

import model.CookieJar;

import java.util.List;
import java.util.ArrayList;

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
    private CookieJar cookieJar;
    private List<CookieJar> cookieJarList;
    private ScheduledExecutorService executor;
    private List<ScheduledExecutorService> executorList;

    // EFFECTS: creates empty lists of cookie jars and executors
    //          prompts user to create a new cookie then starts the game
    public Game() {
        cookieJarList = new ArrayList<>();
        executorList = new ArrayList<>();
        System.out.println("Welcome to Cookie Clicker!");
        makeCookieJar();
        playGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void playGame() {
        Boolean isRunning = true;
        startMessage();
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String command = input.nextLine().toLowerCase();
            if (command.isBlank()) {
                cookieJar.addCookie();
            } else if (command.equals("a")) {
                System.out.println("You currently have " + cookieJar.getNumCookies() + " cookies.");
            } else if (command.equals("s")) {
                cookieHelper();
            } else if (command.equals("z")) {
                report();
                makeCookieJar();
            } else if (command.equals("x")) {
                report();
                isRunning = false;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
        conclude();
    }

    // MODIFIES: this
    // EFFECTS: creates a cookieHelper at the cost of 5 cookies
    //          cookieHelper adds a cookie every 5 seconds. Is stackable
    public void cookieHelper() {
        if (cookieJar.getNumCookies() < 5) {
            System.out.println("Sorry, you do not have enough cookies to purchase a cookie helper.");
        } else {
            cookieJar.buyHelper();
            if (cookieJar.getNumCookies() == 1) {
                System.out.println("You have bought a cookie helper! Each cookie helper gives you one cookie every 5 "
                        + "seconds. You now have " + cookieJar.getNumCookies() + " cookieJar.");
            } else {
                System.out.println("You have bought a cookie helper! Each cookie helper gives you one cookie every 5 "
                        + "seconds. You now have " + cookieJar.getNumCookies() + " cookies.");
            }
            Runnable addCookie = new Runnable() {
                public void run() {
                    cookieJar.addCookie();
                }
            };
            executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(addCookie, 5, 5, TimeUnit.SECONDS);
            executorList.add(executor);
        }
    }

    // MODIFIES: this
    // EFFECTS: terminates all existing cookieHelpers
    //          stores name and cost of the current cookieJar in cookieJarList
    public void report() {
        for (ScheduledExecutorService executor : executorList) {
            executor.shutdown();
        }
        System.out.println("You ended up with " + cookieJar.getNumCookies() + " cookies!"
                + " It is worth a total of $" + cookieJar.getCost() + ". Data has been added to the list.");
        cookieJarList.add(cookieJar);
    }

    // MODIFIES: this
    // EFFECTS: creates a new cookie jar with name and value per cookie set by user
    public void makeCookieJar() {
        Scanner input = new Scanner(System.in);
        System.out.println("What kind of cookie would you like to make? ");
        String name = input.nextLine();
        System.out.println("How much do you want to sell each cookie for (in $)? ");
        int value = Integer.parseInt(input.nextLine());
        cookieJar = new CookieJar(name, value);
    }

    // MODIFIES: this
    // EFFECTS: terminates all existing cookieHelpers
    //          displays information on all cookieJars stored in cookieJarList
    //          computes the total value of all cookies in all cookieJars
    public void conclude() {
        for (ScheduledExecutorService executor : executorList) {
            executor.shutdown();
        }
        int totalCost = 0;
        System.out.println("\nThanks for playing! You ended with " + cookieJarList.size() + " different collections of "
                + "cookies:");
        for (int num = 0; num < cookieJarList.size(); num++) {
            cookieJar = cookieJarList.get(num);
            System.out.println(cookieJar.getNumCookies() + " " + cookieJar.getName() + " cookies - worth $"
                    + cookieJar.getCost());
            totalCost += cookieJar.getCost();
        }
        System.out.println("The total value of all your cookies is $" + totalCost);
    }

    // EFFECTS: displays possible actions for user
    public void startMessage() {
        System.out.println("Here are your options:\n\tEnter - add one cookie\n\ta - obtain information about your "
                + "current cookies\n\ts - purchase a cookie helper that gives you cookies (costs 5 cookies)\n\tz - "
                + "start making different cookies\n\tx - quit");
    }
}
