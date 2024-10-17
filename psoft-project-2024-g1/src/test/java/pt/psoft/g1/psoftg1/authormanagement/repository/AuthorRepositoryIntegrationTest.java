package pt.psoft.g1.psoftg1.authormanagement.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.repositories.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Based on https://www.baeldung.com/spring-boot-testing
 * <p>Adaptations to Junit 5 with ChatGPT
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AuthorRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void whenFindByName_thenReturnAuthor() {
        // given
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        entityManager.persist(alex);
        entityManager.flush();

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
        entityManager.persist(alex);
        entityManager.persist(john);
        entityManager.flush();

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
        entityManager.persist(alex);
        entityManager.flush();

        // when
        authorRepository.delete(alex);
        entityManager.flush();

        // then
        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                                           .collect(Collectors.toList());
        assertThat(authors).isEmpty();
    }

    @Test
    public void whenFindByNonExistentName_thenReturnEmptyList() {
        // given
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        entityManager.persist(alex);
        entityManager.flush();

        // when
        List<Author> list = authorRepository.searchByNameName("NonExistentName");

        // then
        assertThat(list).isEmpty();
    }
}

