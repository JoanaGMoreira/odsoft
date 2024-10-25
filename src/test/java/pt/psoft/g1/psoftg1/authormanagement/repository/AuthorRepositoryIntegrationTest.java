package pt.psoft.g1.psoftg1.authormanagement.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Based on https://www.baeldung.com/spring-boot-testing
 * <p>
 * Adaptations to Junit 5 with ChatGPT
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AuthorRepositoryIntegrationTest {

    @Autowired
    private AuthorRepository authorRepository;

    // Testes já existentes (funcionais de caixa transparente)

    @Test
    public void whenFindByName_thenReturnAuthor() {
        // given
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        authorRepository.save(alex);

        // when
        List<Author> list = authorRepository.searchByNameName(alex.getName());

        // then
        assertThat(list).isNotEmpty();
        assertThat(list.get(0).getName()).isEqualTo(alex.getName());
    }

    @Test
    public void whenFindAllAuthors_thenReturnAllAuthors() {
        // given
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        Author john = new Author("John", "John é um autor", null);
        authorRepository.save(alex);
        authorRepository.save(john);

        // when
        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // then
        assertThat(authors).hasSize(2).extracting(Author::getName).containsExactlyInAnyOrder("Alex", "John");
    }

    @Test
    public void whenDeleteAuthor_thenAuthorShouldBeDeleted() {
        // given
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        authorRepository.save(alex);

        // when
        authorRepository.delete(alex);

        // then
        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        assertThat(authors).isEmpty();
    }

    @Test
    public void whenFindByNonExistentName_thenReturnEmptyList() {
        // given
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        authorRepository.save(alex);

        // when
        List<Author> list = authorRepository.searchByNameName("NonExistentName");

        // then
        assertThat(list).isEmpty();
    }

    // Testes Funcionais de Caixa Opaca

    @Test
    public void givenValidAuthorNumber_whenFindByAuthorNumber_thenReturnAuthor() {
        // Dado um autor válido
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        authorRepository.save(alex);

        // Quando buscado pelo número do autor
        Optional<Author> foundAuthor = authorRepository.findByAuthorNumber(alex.getAuthorNumber());

        // Então deve retornar o autor correto
        assertThat(foundAuthor).isPresent();
        assertThat(foundAuthor.get().getName()).isEqualTo("Alex");
    }

    @Test
    public void givenNonExistentAuthorNumber_whenFindByAuthorNumber_thenReturnEmpty() {
        // Quando um número de autor inexistente é buscado
        Optional<Author> foundAuthor = authorRepository.findByAuthorNumber(999L);

        // Então não deve retornar nada
        assertThat(foundAuthor).isNotPresent();
    }

    //@Test
    public void givenAuthorWithCoAuthors_whenFindCoAuthors_thenReturnCoAuthorsList() {
        // Dado um autor com co-autores
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        Author john = new Author("John", "John é co-autor", null);

        // Supondo que o relacionamento de co-autoria seja armazenado de alguma forma
        // Persistimos os autores no banco de dados
        authorRepository.save(alex);
        authorRepository.save(john);

        // Quando buscado os co-autores pelo número do autor
        List<Author> coAuthors = authorRepository.findCoAuthorsByAuthorNumber(alex.getAuthorNumber());

        // Então deve retornar a lista de co-autores (testa-se o relacionamento de co-autoria)
        assertThat(coAuthors).isNotEmpty();
        assertThat(coAuthors.get(0).getName()).isEqualTo("John");
    }

    @Test
    public void givenNonExistentCoAuthor_whenFindCoAuthors_thenReturnEmptyList() {
        // Quando um número de autor sem co-autores é buscado
        List<Author> coAuthors = authorRepository.findCoAuthorsByAuthorNumber(999L);

        // Então a lista de co-autores deve estar vazia
        assertThat(coAuthors).isEmpty();
    }
}


