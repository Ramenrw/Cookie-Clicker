package persistence;

import model.Bakery;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Citations:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Bakery bakery = reader.readBakery();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBakery() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBakery.json");
        try {
            Bakery bakery = reader.readBakery();
            assertEquals(0, bakery.getNumCookies());
            assertEquals(0, bakery.getHelpers().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderBakery() {
        JsonReader reader = new JsonReader("./data/testReaderBakery.json");
        try {
            Bakery bakery = reader.readBakery();
            assertEquals(3, bakery.getNumCookies());
            assertEquals(2, bakery.getHelpers().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}