package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CookieJarTest {
    CookieJar testCookieJar;

    @BeforeEach
    void runBefore() {
        testCookieJar = new CookieJar("Chocolate chip cookie", 2);
    }

    @Test
    void testConstructor() {
        assertEquals (0, testCookieJar.getNumCookies());
        assertEquals("Chocolate chip cookie", testCookieJar.getName());
        assertEquals(0, testCookieJar.getCost());
    }

    @Test
    void testAddCookie() {
        testCookieJar.addCookie();
        assertEquals (1, testCookieJar.getNumCookies());
    }

    @Test
    void testAddMultipleCookies() {
        testCookieJar.addCookie();
        assertEquals (1, testCookieJar.getNumCookies());
        testCookieJar.addCookie();
        assertEquals (2, testCookieJar.getNumCookies());
    }

    @Test
    void testBuyHelper() {
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.buyHelper();
        assertEquals (0, testCookieJar.getNumCookies());
    }

    @Test
    void testBuyMultipleHelpers() {
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.buyHelper();
        assertEquals (0, testCookieJar.getNumCookies());
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.buyHelper();
        assertEquals (1, testCookieJar.getNumCookies());
    }

    @Test
    void testBuyHelperNotEnough() {
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.addCookie();
        testCookieJar.buyHelper();
        assertEquals (4, testCookieJar.getNumCookies());
    }

}