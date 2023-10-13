package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CookieTest {
    Cookie testCookie;

    @BeforeEach
    void runBefore() {
        testCookie = new Cookie("Chocolate chip cookie", 2);
    }

    @Test
    void testConstructor() {
        assertEquals (0, testCookie.getNumCookies());
        assertEquals("Chocolate chip cookie", testCookie.getName());
        assertEquals(0, testCookie.getCost());
    }

    @Test
    void testAddCookie() {
        testCookie.addCookie();
        assertEquals (1, testCookie.getNumCookies());
    }

    @Test
    void testAddMultipleCookies() {
        testCookie.addCookie();
        assertEquals (1, testCookie.getNumCookies());
        testCookie.addCookie();
        assertEquals (2, testCookie.getNumCookies());
    }

    @Test
    void testBuyHelper() {
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.buyHelper();
        assertEquals (0, testCookie.getNumCookies());
    }

    @Test
    void testBuyMultipleHelpers() {
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.buyHelper();
        assertEquals (0, testCookie.getNumCookies());
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.addCookie();
        testCookie.buyHelper();
        assertEquals (1, testCookie.getNumCookies());
    }

}