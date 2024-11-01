package pt.psoft.g1.psoftg1.shared.services;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.shared.model.Photo;

import java.nio.file.Path;

@Component
public class PhotoMapper {
    public Photo map(String photoURI) {
        return new Photo(Path.of(photoURI));
    }
}
