package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.MySQLEntityWithPhoto;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.NameEntity;

@Entity
@Table(name = "author")
public class AuthorEntity extends MySQLEntityWithPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_NUMBER")
    @Getter @Setter
    private Long authorNumber;

    @Version
    @Getter @Setter
    private long version;

    @Embedded
    @Getter @Setter
    private NameEntity name;

    @Embedded
    @Getter @Setter
    private BioEntity bio;


    public AuthorEntity() {
        // got ORM only
    }

}
