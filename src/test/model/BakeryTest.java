package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BakeryTest {
    Bakery testBakery;

    @BeforeEach
    void runBefore() {
        testBakery = new Bakery();
    }

    @Test
    void testConstructor() {
        assertEquals (0, testBakery.getNumCookies());
    }

    @Test
    void testAddCookie() {
        testBakery.addCookie();
        assertEquals (1, testBakery.getNumCookies());
    }

    @Test
    void testAddMultipleCookies() {
        testBakery.addCookie();
        assertEquals (1, testBakery.getNumCookies());
        testBakery.addCookie();
        assertEquals (2, testBakery.getNumCookies());
    }

    @Test
    void testBuyHelper() {
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.buyHelper();
        assertEquals (0, testBakery.getNumCookies());
    }

    @Test
    void testBuyMultipleHelpers() {
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.buyHelper();
        assertEquals (0, testBakery.getNumCookies());
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.buyHelper();
        assertEquals (1, testBakery.getNumCookies());
    }

}