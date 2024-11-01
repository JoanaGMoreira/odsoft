package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long pk;

    @NotNull
    @Setter
    @Getter
    private String photoFile;

    public PhotoEntity(){}
}
