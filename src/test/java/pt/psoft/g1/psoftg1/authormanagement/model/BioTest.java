package pt.psoft.g1.psoftg1.authormanagement.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BioTest {

    @Test
    void ensureBioMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Bio(null));
    }

    @Test
    void ensureBioMustNotBeBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Bio(""));
    }


    /**
     * Text from <a href="https://www.lipsum.com/">Lorem Ipsum</a> generator.
     */
    @Test
    void ensureBioMustNotHave4097Characters() {
        var bio = new Faker().lorem().characters(4097);
        System.out.printf("Bio length: %d\n", bio.length());
        assertThrows(IllegalArgumentException.class, () -> new Bio(bio));
    }

    @Test
    void ensureBioCanBeSetWith4096Characters() {
        var someBio = new Faker().lorem().characters(4096);
        var bio = new Bio(someBio);
        assertEquals(someBio, bio.toString());
    }

    @Test
    void ensureBioCanBeChanged() {
        final var bio = new Bio("Some bio");
        bio.setBio("Some other bio");
        assertEquals("Some other bio", bio.toString());
    }
}
