package pt.psoft.g1.psoftg1.authormanagement.model;

import org.hibernate.StaleObjectStateException;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import pt.psoft.g1.psoftg1.authormanagement.services.CreateAuthorRequest;
import pt.psoft.g1.psoftg1.authormanagement.services.UpdateAuthorRequest;
import pt.psoft.g1.psoftg1.shared.model.EntityWithPhoto;
import pt.psoft.g1.psoftg1.shared.model.Photo;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class AuthorTest {
    private final String validName = "João Alberto";
    private final String validBio = "O João Alberto nasceu em Chaves e foi pedreiro a maior parte da sua vida.";

    private final UpdateAuthorRequest request = new UpdateAuthorRequest(validName, validBio, null, null);

    private static class EntityWithPhotoImpl extends EntityWithPhoto { }

    @Test
    void ensureNameNotNull(){
        assertThrows(IllegalArgumentException.class, () -> new Author(null,validBio, null));
    }

    @Test
    void ensureBioNotNull(){
        assertThrows(IllegalArgumentException.class, () -> new Author(validName,null, null));
    }

    @Test
    void whenVersionIsStaleItIsNotPossibleToPatch() {
        final var subject = new Author(validName,validBio, null);

        assertThrows(StaleObjectStateException.class, () -> subject.applyPatch(999, request));
    }

    @Test
    void testCreateAuthorWithoutPhoto() {
        Author author = new Author(validName, validBio, null);
        assertNotNull(author);
        assertNull(author.getPhoto());
    }

    @Test
    void testCreateAuthorRequestWithPhoto() {
        CreateAuthorRequest request = new CreateAuthorRequest(validName, validBio, null, "photoTest.jpg");
        Author author = new Author(request.getName(), request.getBio(), "photoTest.jpg");
        assertNotNull(author);
        assertEquals(request.getPhotoURI(), author.getPhoto().getPhotoFile());
    }

    @Test
    void testCreateAuthorRequestWithoutPhoto() {
        CreateAuthorRequest request = new CreateAuthorRequest(validName, validBio, null, null);
        Author author = new Author(request.getName(), request.getBio(), null);
        assertNotNull(author);
        assertNull(author.getPhoto());
    }

    @Test
    void testEntityWithPhotoSetPhotoInternalWithValidURI() {
        EntityWithPhoto entity = new EntityWithPhotoImpl();
        String validPhotoURI = "photoTest.jpg";
        entity.setPhoto(validPhotoURI);
        assertNotNull(entity.getPhoto());
    }

    @Test
    void ensurePhotoCanBeNull_AkaOptional() {
        Author author = new Author(validName, validBio, null);
        assertNull(author.getPhoto());
    }

    @Test
    void ensureValidPhoto() {
        Author author = new Author(validName, validBio, "photoTest.jpg");
        Photo photo = author.getPhoto();
        assertNotNull(photo);
        assertEquals("photoTest.jpg", photo.getPhotoFile());
    }

    @Test
    void returnVersion_whenVersionIsSet() {
        Author author = new Author(validName, validBio, null);

        assertEquals(0L, author.getVersion().longValue());
    }

//    @Test
//    void whenRemovePhoto_thenDesiredVersionMustNotBeNegative() {
//        Author author = new Author(validName, validBio, "photoTest.jpg");
//        assert
//    }

    //Transparent Box Testing

    @Test
    void whenApplyPatch_thenAuthorBookRequestGettersAreAllCalledOnce() {
        //arrange
        Author mockAuthor = new Author("Luís de Camões", "Luís Vaz de Camões foi um poeta de Portugal", null);
        UpdateAuthorRequest mockRequest = mock(UpdateAuthorRequest.class);

        //act
        Long currentVersion = mockAuthor.getVersion();
        mockAuthor.applyPatch(currentVersion, mockRequest);
        //assert
        verify(mockRequest, times(1)).getName();
        verify(mockRequest, times(1)).getBio();
        verify(mockRequest, times(1)).getPhotoURI();
    }

    @Test
    void whenApplyPatchWithNullValues_thenSettersAreNeverCalled(){
        // Arrange

        Author mockedAuthor = mock(Author.class);
        UpdateAuthorRequest updateBookRequest = new UpdateAuthorRequest(
                null,
                null, mock(MultipartFile.class), null);

        // Act
        mockedAuthor.applyPatch(1L, updateBookRequest);

        // Assert
        verify(mockedAuthor, never()).setBio(anyString());
        verify(mockedAuthor, never()).setName(anyString());
        verify(mockedAuthor, never()).setPhotoInternal(anyString());
    }
}

