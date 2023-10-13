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

    public void buyHelper() {
        this.numCookies -= 5;
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
