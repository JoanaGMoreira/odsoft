package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class IsbnEntity {

    @Size(min = 10, max = 13)
    @Column(name="ISBN", length = 16)
    private String isbn;

    public IsbnEntity() {};
}
