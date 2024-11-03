package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "author")
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
