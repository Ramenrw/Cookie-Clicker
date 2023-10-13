package ui;

import model.Cookie;

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

// Game
public class Game {
    Cookie cookie;
    List<Cookie> cookieList;
    ScheduledExecutorService executor;
    List<ScheduledExecutorService> executorList;

    // EFFECTS: constructs a cookie clicker game with
    public Game() {
        cookieList = new ArrayList<>();
        executorList = new ArrayList<>();
        System.out.println("Welcome to Cookie Clicker!");
        makeCookie();
        playGame();
    }

    public void playGame() {
        Boolean isRunning = true;
        startMessage();
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String command = input.nextLine().toLowerCase();
            if (command.isBlank()) {
                cookie.addCookie();
            } else if (command.equals("a")) {
                System.out.println("You currently have " + cookie.getNumCookies() + " cookies.");
            } else if (command.equals("s")) {
                cookieHelper();
            } else if (command.equals("z")) {
                report();
                makeCookie();
            } else if (command.equals("x")) {
                report();
                isRunning = false;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
        conclude();
    }

    public void cookieHelper() {
        if (cookie.getNumCookies() < 5) {
            System.out.println("Sorry, you do not have enough cookies to purchase a cookie helper.");
        } else {
            cookie.buyHelper();
            if (cookie.getNumCookies() == 1) {
                System.out.println("You have bought a cookie helper! Each cookie helper gives you one cookie every 5 "
                        + "seconds. You now have " + cookie.getNumCookies() + " cookie.");
            } else {
                System.out.println("You have bought a cookie helper! Each cookie helper gives you one cookie every 5 "
                        + "seconds. You now have " + cookie.getNumCookies() + " cookies.");
            }
            Runnable addCookie = new Runnable() {
                public void run() {
                    cookie.addCookie();
                }
            };
            executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(addCookie, 5, 5, TimeUnit.SECONDS);
            executorList.add(executor);
        }
    }

    public void report() {
        for (ScheduledExecutorService executor : executorList) {
            executor.shutdown();
        }
        System.out.println("You ended up with " + cookie.getNumCookies() + " cookies!"
                + " It is worth a total of $" + cookie.getCost() + ". Data has been added to the list.");
        cookieList.add(cookie);
    }

    public void makeCookie() {
        Scanner input = new Scanner(System.in);
        System.out.println("What kind of cookie would you like to make? ");
        String name = input.nextLine();
        System.out.println("How much do you want to sell each cookie for (in $)? ");
        int value = Integer.parseInt(input.nextLine());
        cookie = new Cookie(name, value);
    }

    public void conclude() {
        for (ScheduledExecutorService executor : executorList) {
            executor.shutdown();
        }
        int totalCost = 0;
        System.out.println("\nThanks for playing! You ended with " + cookieList.size() + " different collections of "
                + "cookies:");
        for (int num = 0; num < cookieList.size(); num++) {
            cookie = cookieList.get(num);
            System.out.println(cookie.getNumCookies() + " " + cookie.getName() + " cookies - worth $"
                    + cookie.getCost());
            totalCost += cookie.getCost();
        }
        System.out.println("The total value of all your cookies is $" + totalCost);
    }

    public void startMessage() {
        System.out.println("Here are your options:\n\tEnter - add one cookie\n\ta - obtain information about your "
                + "current cookies\n\ts - purchase a cookie helper that gives you cookies (costs 5 cookies)\n\tz - "
                + "start making different cookies\n\tx - quit");
    }
}
