package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static pt.psoft.g1.psoftg1.authormanagement.model.Bio.BIO_MAX_LENGTH;


@Embeddable
public class BioEntity {

    @Column(nullable = false, length = BIO_MAX_LENGTH)
    @NotNull
    @Size(min = 1, max = BIO_MAX_LENGTH)
    @Getter @Setter
    private String bio;

    public BioEntity() {
    }

}
