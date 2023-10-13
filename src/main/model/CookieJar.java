package model;

// represents a cookie jar with number of cookies, name, and value of each cookie
public class CookieJar {
    private int numCookies;
    private String name;
    private int value;

    // REQUIRES: name has a non-zero length
    // MODIFIES: this
    // EFFECTS: sets name and value depending on user input
    //          sets number of cookies as 0
    public CookieJar(String name, int value) {
        this.numCookies = 0;
        this.name = name;
        this.value = value;
    }

    // MODIFIES: this
    // EFFECTS: adds 1 cookie to the cookie jar
    public void addCookie() {
        this.numCookies++;
    }

    // MODIFIES: this
    // EFFECTS: subtracts 5 from cookie jar if number of cookies there is greater or equal to 5
    public void buyHelper() {
        if (this.numCookies >= 5) {
            this.numCookies -= 5;
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
