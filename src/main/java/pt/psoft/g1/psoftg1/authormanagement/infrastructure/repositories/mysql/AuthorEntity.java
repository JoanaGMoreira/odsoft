package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.MySQLEntityWithPhoto;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.NameEntity;

@Setter
@Getter
@Entity
@Table(name = "author")
public class AuthorEntity extends MySQLEntityWithPhoto {
    @Id
    @Column(name = "AUTHOR_NUMBER")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorNumber;

    @Version
    private long version;

    @Embedded
    private NameEntity name;

    @Embedded
    private BioEntity bio;


    public AuthorEntity() {
        // got ORM only
    }

}
