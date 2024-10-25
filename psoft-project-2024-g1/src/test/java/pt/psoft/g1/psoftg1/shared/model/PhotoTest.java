package pt.psoft.g1.psoftg1.shared.model;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    // Caixa Opaca: Verificar se o caminho da foto não pode ser nulo
    @Test
    void ensurePathMustNotBeNull() {
        assertThrows(NullPointerException.class, () -> new Photo(null), "Path cannot be null");
    }

    // Caixa Opaca: Verificar se o caminho da foto é configurado corretamente para um arquivo local
    @Test
    void ensurePathIsValidToLocalFile() {
        Path fileStorageLocation = Paths.get("uploads-psoft-g1").toAbsolutePath().normalize();
        assertNotNull(fileStorageLocation.toString());

        Photo photo = new Photo(Paths.get("photoTest.jpg"));
        assertEquals("photoTest.jpg", photo.getPhotoFile());
    }

    // Caixa Transparente: Verificar o construtor protegido para ORM
    @Test
    void ensureProtectedConstructorForORMOnly() {
        Photo photo = new Photo();
        assertNull(photo.getPhotoFile(), "photoFile should be null initially for ORM usage");
    }

    // Caixa Transparente: Testar diretamente setPhotoFile e getPhotoFile
    @Test
    void ensurePhotoFileSetAndGetWorks() {
        Photo photo = new Photo(Paths.get("initialPhoto.jpg"));
        photo.setPhotoFile("updatedPhoto.jpg");
        assertEquals("updatedPhoto.jpg", photo.getPhotoFile(), "photoFile should reflect the latest value set");
    }

    // Caixa Transparente: Testar a anotação @NotNull em photoFile
    @Test
    void ensurePhotoFileIsNotNull() {
        Photo photo = new Photo(Paths.get("validPhoto.jpg"));
        assertNotNull(photo.getPhotoFile(), "photoFile should not be null after initialization");

        // Teste para garantir que photoFile não aceita valores nulos (será necessário tratamento adicional para verificar a anotação @NotNull)
        assertThrows(NullPointerException.class, () -> photo.setPhotoFile(null), "Setting photoFile to null should throw an exception");
    }
}
