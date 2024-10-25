package pt.psoft.g1.psoftg1.shared.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.nio.file.Path;

@Entity
@Document
public class Photo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long pk;

    @NotNull
    @Setter
    @Getter
    private String photoFile;

    protected Photo (){}

    public Photo (Path photoPath){
        setPhotoFile(photoPath.toString());
    }
}

