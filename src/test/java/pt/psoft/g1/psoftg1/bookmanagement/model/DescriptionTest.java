package pt.psoft.g1.psoftg1.bookmanagement.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    void ensureDescriptionCanBeNull() {
        assertDoesNotThrow(() -> new Description(null));
    }

    /**
     * Text from <a href="https://www.lipsum.com/">Lorem Ipsum</a> generator.
     */
    //@Test
    void ensureDescriptionMustNotBeOversize() {
        assertThrows(IllegalArgumentException.class, () -> new Description("... (long text omitted for brevity)"));
    }

    @Test
    void ensureDescriptionIsSet() {
        final var description = new Description("Some description");
        assertEquals("Some description", description.toString());
    }

    @Test
    void ensureDescriptionIsChanged() {
        final var description = new Description("Some description");
        description.setDescription("Some other description");
        assertEquals("Some other description", description.toString());
    }

    // ---------------------- Testes Caixa Preta ----------------------

    @Test
    void ensureEmptyDescriptionIsAllowed() {
        //Verifica se uma descrição vazia é permitida
        assertDoesNotThrow(() -> new Description(""));
    }

    //@Test
    void ensureDescriptionHandlesWhitespace() {
        // Verifica se espaços em branco são tratados corretamente
        Description description = new Description("   ");
        assertEquals("   ", description.toString());
    }

    @Test
    void ensureDescriptionCanBeTrimmed() {
        //Verifica se a descrição com espaços em volta funciona corretamente
        Description description = new Description("   Some description   ");
        assertEquals("   Some description   ", description.toString());
    }

    // ---------------------- Testes Transparentes ----------------------

    @Test
    void ensureDescriptionChangesAreStoredCorrectly() {
        // Verifica que a mudança na descrição altera o estado interno
        Description description = new Description("Initial description");
        description.setDescription("Updated description");
        assertEquals("Updated description", description.toString());
    }

    @Test
    void ensureNullDescriptionChangesToValidString() {
        // Verifica o comportamento de mudança para uma descrição nula
        Description description = new Description("Initial description");
        description.setDescription(null);
        assertNull(description.toString());  // Se a descrição é null, o método toString deve retornar null
    }

    //@Test
    void ensureOversizeDescriptionThrowsErrorAfterUpdate() {
        // Garante que uma atualização com descrição grande lança exceção
        Description description = new Description("Valid description");
        assertThrows(IllegalArgumentException.class, () -> description.setDescription("... (long text omitted for brevity)"));
    }
}
