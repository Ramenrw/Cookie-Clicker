package ui;

import model.Cookie;
//import model.CookieHelper;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Citations: https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds
// and https://github.students.cs.ubc.ca/CPSC210/TellerApp

// Game
public class Game {
    Cookie cookie;
//    CookieHelper cookieHelper;

    public Game() {
        makeCookie();
        playGame();
    }

    public void playGame() {
        Boolean isRunning = true;
        System.out.println("Here are your options:\n\tSpacebar - add one cookie\n\ta - obtain information about your "
                + "current cookies\n\ts - purchase a cookie helper that gives you cookies (costs 5 cookies)\n\tz - "
                + "start making different cookies\n\tx - quit");
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
                makeCookie();
            } else if (command.equals("x")) {
                isRunning = false;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
        System.out.println("Thanks for playing! You ended with " + cookie.getNumCookies() + " cookies!");
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


    public void makeCookie() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Cookie Clicker! What kind of cookie would you like to make? ");
        String name = input.nextLine();
        System.out.println("How much do you want to sell each cookie for (in $)? ");
        int value =  Integer.parseInt(input.nextLine());
        cookie = new Cookie(name, value);
    }

}
