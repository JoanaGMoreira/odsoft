package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql.AuthorEntity;
import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mysql.GenreEntity;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.MySQLEntityWithPhoto;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Book", uniqueConstraints = {
        @UniqueConstraint(name = "uc_book_isbn", columnNames = {"ISBN"})
})
public class BookEntity extends MySQLEntityWithPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Version
    private Long version;

    @Embedded
    private IsbnEntity isbn;

    @Embedded
    @NotNull
    private TitleEntity title;

    @ManyToOne
    @NotNull
    private GenreEntity genre;

    @ManyToMany
    private List<AuthorEntity> authors;

    @Embedded
    private DescriptionEntity description;

    public BookEntity() {
        // got ORM only
    }
}
