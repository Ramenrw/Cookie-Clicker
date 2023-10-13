package ui;

import model.Cookie;

import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Citations: https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds
// and https://github.students.cs.ubc.ca/CPSC210/TellerApp

// Game
public class Game {
    Cookie cookie;
    List<Cookie> cookieList;

    public Game() {
        cookieList = new ArrayList<>();
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
                isRunning = false;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
        conclude();
    }

    public void cookieHelper() {
        if (cookie.buyCookieHelper()) {
            Runnable addCookie = new Runnable() {
                public void run() {
                    cookie.addCookie();
                }
            };

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(addCookie, 5, 5, TimeUnit.SECONDS);
        }
    }

    public void report() {
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

    public void startMessage() {
        System.out.println("Here are your options:\n\tSpacebar - add one cookie\n\ta - obtain information about your "
                + "current cookies\n\ts - purchase a cookie helper that gives you cookies (costs 5 cookies)\n\tz - "
                + "start making different cookies\n\tx - quit");
    }

    public void conclude() {
        int totalCost = 0;
        System.out.println("Thanks for playing! You ended with " + cookieList.size() + " different collections of"
                + "cookies:");
        for (int num = 0; num < cookieList.size(); num++) {
            cookie = cookieList.get(num);
            System.out.println("You had " + cookie.getNumCookies() + cookie.getName() + " cookies. They were worth $"
                    + cookie.getCost() + "!");
            totalCost += cookie.getCost();
        }
        System.out.println("The total value of all your cookies is $" + totalCost);
    }
}
