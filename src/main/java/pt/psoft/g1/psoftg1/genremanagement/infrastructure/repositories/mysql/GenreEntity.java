package pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mysql;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Transient;

import static pt.psoft.g1.psoftg1.genremanagement.model.Genre.GENRE_MAX_LENGTH;

@Entity
@Table(name = "genre")
@Embeddable
public class GenreEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    long pk;

    @Size(min = 1, max = GENRE_MAX_LENGTH, message = "Genre name must be between 1 and 100 characters")
    @Column(unique=true, nullable=false, length = GENRE_MAX_LENGTH)
    @Getter
    @Setter
    private String genre;

    public GenreEntity(){}
}
