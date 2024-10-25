package pt.psoft.g1.psoftg1.shared.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    void ensureNameMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Name(null));
    }

    @Test
    void ensureNameMustNotBeBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Name(""));
    }

    @Test
    void ensureNameMustOnlyBeAlphanumeric() {
        assertThrows(IllegalArgumentException.class, () -> new Name("Ricardo!"));
    }


    /**
     * Text from <a href="https://www.lipsum.com/">Lorem Ipsum</a> generator.
     */
    @Test
    void ensureNameMustNotBeOversize() {
        assertThrows(IllegalArgumentException.class, () -> new Name("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut fermentum venenatis augue, a congue turpis eleifend ut. Etiam fringilla ex nulla, id quis."));
    }

    @Test
    void ensureNameIsSet() {
        final var name = new Name("Some name");
        assertEquals("Some name", name.toString());
    }

    @Test
    void ensureNameIsChanged() {
        final var name = new Name("Some name");
        name.setName("Some other name");
        assertEquals("Some other name", name.toString());
    }


    @Test
    void ensureProtectedConstructorForORMOnly() {
        Name name = new Name(); // Não deve lançar nenhuma exceção pois é usado apenas pelo ORM
        assertNull(name.getName()); // Verifica se o nome é nulo até ser definido
    }


    @Test
    void ensureSetNameWithBoundaryLength() {
        String validName = "a".repeat(150); // Gera uma string com exatamente 150 caracteres
        final var name = new Name(validName);
        assertEquals(validName, name.toString());
    }


    @Test
    void ensureSetNameWithWhitespaceOrSpecialChars() {
        assertThrows(IllegalArgumentException.class, () -> new Name("John Doe"), "Name can only contain alphanumeric characters");
        assertThrows(IllegalArgumentException.class, () -> new Name("Maria_Silva"), "Name can only contain alphanumeric characters");
    }


    @Test
    void ensureIsAlphanumericWorksCorrectly() {
        assertTrue(StringUtilsCustom.isAlphanumeric("Ricardo123"));
        assertFalse(StringUtilsCustom.isAlphanumeric("Ricardo!"));
        assertFalse(StringUtilsCustom.isAlphanumeric(" "));
    }
}
