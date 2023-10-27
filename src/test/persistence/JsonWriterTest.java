package persistence;

import model.Bakery;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Citations:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Bakery bakery = new Bakery();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBakery() {
        try {
            Bakery bakery = new Bakery();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBakery.json");
            writer.open();
            writer.write(bakery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBakery.json");
            bakery = reader.read();
            assertEquals(0, bakery.getNumCookies());
            assertEquals(0, bakery.getHelpers().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterBakery() {
        try {
            Bakery bakery = new Bakery();
            bakery.addCookie();
            bakery.addCookie();
            bakery.addCookie();
            bakery.addHelper();
            bakery.addHelper();
            JsonWriter writer = new JsonWriter("./data/testWriterBakery.json");
            writer.open();
            writer.write(bakery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBakery.json");
            bakery = reader.read();
            assertEquals(3, bakery.getNumCookies());
            assertEquals(2, bakery.getHelpers().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}