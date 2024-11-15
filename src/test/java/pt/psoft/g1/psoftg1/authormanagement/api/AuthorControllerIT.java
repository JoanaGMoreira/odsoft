package pt.psoft.g1.psoftg1.authormanagement.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pt.psoft.g1.psoftg1.authormanagement.services.AuthorService;
import pt.psoft.g1.psoftg1.authormanagement.services.CreateAuthorRequest;
import pt.psoft.g1.psoftg1.bookmanagement.api.BookViewMapper;
import pt.psoft.g1.psoftg1.shared.services.ConcurrencyService;
import pt.psoft.g1.psoftg1.shared.services.FileStorageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthorControllerIT {

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorViewMapper authorViewMapper;

    @Autowired
    ConcurrencyService concurrencyService;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    BookViewMapper bookViewMapper;


    @Autowired
    AuthorController authorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturn201WhenCreatingAuthor(){
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest("Alex", "O Alex escreveu livros",null, null);

        // Act
        ResponseEntity<AuthorView> responseEntity = authorController.create(createAuthorRequest);

        // Assert
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void shouldReturn200WhenFindingAuthorByNumber(){
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest("Alex", "O Alex escreveu livros",null, null);

        // Act
        ResponseEntity<AuthorView> authorViewResponseEntity = authorController.create(createAuthorRequest);
        assertEquals(authorViewResponseEntity.getStatusCode(), HttpStatus.CREATED);

        // Assert
        ResponseEntity<AuthorView> responseEntity = authorController.findByAuthorNumber(authorViewResponseEntity.getBody().getAuthorNumber());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseEntity.getBody());
    }




}
