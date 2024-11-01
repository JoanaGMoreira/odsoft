package pt.psoft.g1.psoftg1.shared.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;

@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @NotNull
    @Setter
    @Getter
    private String photoFile;


    public Photo(Path photoPath){
        setPhotoFile(photoPath.toString());
    }
}

