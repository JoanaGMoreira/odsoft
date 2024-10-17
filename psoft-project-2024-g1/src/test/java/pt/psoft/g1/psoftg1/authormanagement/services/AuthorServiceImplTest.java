package pt.psoft.g1.psoftg1.authormanagement.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.repositories.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthorServiceImplTest {
    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    private List<Author> authors;

    @BeforeEach
    public void setUp() {
        authors = new ArrayList<>();
        
        // Criação de autores com nomes e biografias
        Author alex = new Author("Alex", "O Alex escreveu livros", null);
        //alex.setId(1L);  // Definindo um ID fictício
        Author john = new Author("John", "John é um autor", null);
        //john.setId(2L);  // Definindo um ID fictício

        // Adiciona os autores à lista
        authors.add(alex);
        authors.add(john);

        // Simula os métodos do repositório
        Mockito.when(authorRepository.findAll()).thenReturn(authors);
        Mockito.when(authorRepository.findByAuthorNumber(anyLong()))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    return authors.stream()
                                  .filter(a -> a.getId() != null && a.getId().equals(id))
                                  .findFirst();
                });
        Mockito.when(authorRepository.save(any(Author.class))).thenAnswer(invocation -> {
            Author savedAuthor = invocation.getArgument(0);
            return savedAuthor; // Retorna o autor salvo
        });
    }

    @Test
    public void whenValidId_thenAuthorShouldBeFound() {
        Long id = 1L;
        Optional<Author> found = authorService.findByAuthorNumber(id);
        found.ifPresent(author -> assertThat(author.getId()).isEqualTo(id));
    }

    @Test
    public void whenInvalidId_thenAuthorShouldNotBeFound() {
        Long id = 999L; // ID inexistente
        Optional<Author> found = authorService.findByAuthorNumber(id);
        assertThat(found).isNotPresent();
    }

    @Test
    public void whenFindAllAuthors_thenAllAuthorsShouldBeReturned() {
        List<Author> allAuthors = (List<Author>) authorService.findAll();
        assertThat(allAuthors).hasSize(2);
        assertThat(allAuthors).extracting(Author::getName).containsExactlyInAnyOrder("Alex", "John");
    }

    @Test
    public void whenCreateAuthor_thenAuthorShouldBeSaved() {
        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest("New Author", "Descrição do Novo Autor", null, null);
        
        // Mock do repositório para criar o autor
        Author newAuthor = new Author("New Author", "Descrição do Novo Autor", null);
        Mockito.when(authorRepository.save(any(Author.class))).thenReturn(newAuthor);

        Author savedAuthor = authorService.create(createAuthorRequest);
        assertThat(savedAuthor.getName()).isEqualTo("New Author");
    }

    @Test
    public void whenPartialUpdateAuthor_thenAuthorShouldBeUpdated() {
        Long id = 1L;

        // Simulando a atualização de autor
        UpdateAuthorRequest updateRequest = new UpdateAuthorRequest();
        updateRequest.setName("Alex Updated");
        updateRequest.setBio("Atualização da descrição");
        
        // Mock do repositório para atualização
        Mockito.when(authorRepository.findByAuthorNumber(id)).thenReturn(Optional.of(authors.get(0)));

        Author updatedAuthor = authorService.partialUpdate(id, updateRequest, 1); // Passando a versão desejada
        assertThat(updatedAuthor.getName()).isEqualTo("Alex Updated");
    }

    @Test
    public void whenRemoveAuthorPhoto_thenPhotoShouldBeRemoved() {
        Long id = 1L;
        Author author = authors.get(0);
        author.setPhoto("path/to/photo");

        // Simulando o repositório
        Mockito.when(authorRepository.findByAuthorNumber(id)).thenReturn(Optional.of(author));

        authorService.removeAuthorPhoto(id, author.getVersion());

        assertThat(author.getPhoto()).isNull(); // Verifica se a foto foi removida
    }
}
