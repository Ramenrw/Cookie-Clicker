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
    void testBuyHelperNotEnough() {
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.buyHelper();
        assertEquals(4, testBakery.getNumCookies());
        assertEquals(0, testBakery.getHelpers().size());
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
        assertEquals (1, testBakery.getHelpers().size());
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
        assertEquals (1, testBakery.getHelpers().size());
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.buyHelper();
        assertEquals (1, testBakery.getNumCookies());
        assertEquals (2, testBakery.getHelpers().size());
    }

    @Test
    void testRemoveHelper() {
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.buyHelper();
        assertEquals (0, testBakery.getNumCookies());
        assertEquals (1, testBakery.getHelpers().size());
        testBakery.removeHelper();
        assertEquals (0, testBakery.getHelpers().size());
    }

    @Test
    void testRemoveHelperNothingThere() {
        testBakery.removeHelper();
        assertEquals (0, testBakery.getHelpers().size());
    }

    @Test
    void testRemoveHelpers() {
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.addCookie();
        testBakery.buyHelper();
        testBakery.buyHelper();
        assertEquals (0, testBakery.getNumCookies());
        assertEquals (2, testBakery.getHelpers().size());
        testBakery.removeHelper();
        assertEquals (1, testBakery.getHelpers().size());
        testBakery.removeHelper();
        assertEquals (0, testBakery.getHelpers().size());
    }
}