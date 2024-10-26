package pt.psoft.g1.psoftg1.bookmanagement.model;

import org.junit.jupiter.api.BeforeEach;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private final String validIsbn = "9782826012092";
    private final String validTitle = "Encantos de contar";
    private final Author validAuthor1 = new Author("João Alberto", "O João Alberto nasceu em Chaves e foi pedreiro a maior parte da sua vida.", null);
    private final Author validAuthor2 = new Author("Maria José", "A Maria José nasceu em Viseu e só come laranjas às segundas feiras.", null);
    private final Genre validGenre = new Genre("Fantasia");
    private ArrayList<Author> authors = new ArrayList<>();

    @BeforeEach
    void setUp(){
        authors.clear();
    }

    // ---------------------- Testes Caixa Preta ----------------------

    @Test
    void ensureBookIsCreatedWithValidData() {
        authors.add(validAuthor1);
        authors.add(validAuthor2);

        // valida que o livro é criado corretamente com dados válidos
        Book book = new Book(validIsbn, validTitle, "Descrição sobre o livro.", validGenre, authors, "photoURI");

        assertEquals(validIsbn, book.getIsbn());
        assertEquals(validTitle, book.getTitle());
        assertEquals(validGenre, book.getGenre());
        assertEquals(authors, book.getAuthors());
        assertEquals("photoURI", book.getPhoto().getPhotoFile()); // Verifica a foto URI
    }

    @Test
    void ensureIsbnCannotBeEmpty() {
        authors.add(validAuthor1);

        // valida que um ISBN vazio lança exceção
        assertThrows(IllegalArgumentException.class, () -> new Book("", validTitle, null, validGenre, authors, null));
    }

    @Test
    void ensureTitleCannotBeEmpty() {
        authors.add(validAuthor1);

        // valida que um título vazio lança exceção
        assertThrows(IllegalArgumentException.class, () -> new Book(validIsbn, "", null, validGenre, authors, null));
    }

    @Test
    void ensureGenreCannotBeEmpty() {
        authors.add(validAuthor1);

        // valida que um gênero vazio lança exceção
        assertThrows(IllegalArgumentException.class, () -> new Book(validIsbn, validTitle, null, null, authors, null));
    }

    @Test
    void ensureAuthorsListCannotBeEmpty() {
        // validando que a lista de autores não pode estar vazia
        assertThrows(IllegalArgumentException.class, () -> new Book(validIsbn, validTitle, null, validGenre, authors, null));
    }

    // ---------------------- Testes Transparentes ----------------------

    @Test
    void ensureBookThrowsExceptionWhenAuthorsAreNull() {
        // verifica a lógica interna para autores nulos
        assertThrows(IllegalArgumentException.class, () -> new Book(validIsbn, validTitle, null, validGenre, null, null));
    }

    @Test
    void ensureBookCanBeCreatedWithoutPhoto() {
        authors.add(validAuthor1);

        // verifica se o livro pode ser criado sem uma foto (photoURI nula)
        Book book = new Book(validIsbn, validTitle, "Descrição", validGenre, authors, null);

        assertNull(book.getPhoto()); // Verifica que não há foto associada
    }

    @Test
    void ensureMultipleAuthorsCanBeAdded() {
        //verifica que múltiplos autores podem ser adicionados
        authors.add(validAuthor1);
        authors.add(validAuthor2);

        Book book = new Book(validIsbn, validTitle, "Descrição", validGenre, authors, null);

        // Verificando que os autores são atribuídos corretamente
        assertTrue(book.getAuthors().contains(validAuthor1));
        assertTrue(book.getAuthors().contains(validAuthor2));
    }

    @Test
    void ensurePhotoIsAssignedCorrectly() {
        authors.add(validAuthor1);

        //valida a atribuição de uma URI de foto válida
        Book book = new Book(validIsbn, validTitle, "Descrição", validGenre, authors, "validPhotoURI");

        assertEquals("validPhotoURI", book.getPhoto().getPhotoFile());
    }

    @Test
    void ensureBookWithEmptyDescriptionIsAllowed() {
        authors.add(validAuthor1);

        //verifica se um livro pode ser criado sem descrição
        Book book = new Book(validIsbn, validTitle, "", validGenre, authors, null);

        assertEquals("", book.getDescription());  // Deve permitir descrição vazia
    }
}
