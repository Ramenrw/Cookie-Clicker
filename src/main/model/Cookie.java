package model;

// represents a cookie object

public class Cookie {
    private int numCookies;
    private String name;
    private int value;

    public Cookie(String name, int value) {
        this.numCookies = 0;
        this.name = name;
        this.value = value;
    }

    public void addCookie() {
        this.numCookies++;
    }

    public boolean buyCookieHelper() {
        if (this.numCookies < 5) {
            System.out.println("Sorry, you do not have enough cookies to purchase a cookie helper.");
            return false;
        } else {
            this.numCookies -= 5;
            if (this.numCookies == 1) {
                System.out.println("You have bought a cookie helper! Each cookie helper gives you one cookie each "
                        + "second. You now have " + numCookies + " cookie.");
            } else {
                System.out.println("You have bought a cookie helper! Each cookie helper gives you one cookie each "
                        + "second. You now have " + numCookies + " cookies.");
            }
            return true;
        }
    }

    public int getNumCookies() {
        return this.numCookies;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.numCookies * this.value;
    }

}
