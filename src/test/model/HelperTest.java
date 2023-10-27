package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelperTest {
    Helper testHelper;

    @BeforeEach
    void runBefore() {
        testHelper = new Helper();
    }

    @Test
    void testConstructor() {
        assertEquals (5, testHelper.getCost());
        assertEquals (5, testHelper.getSecondsPerCookie());
    }
}