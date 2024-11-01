package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mysql.GenreEntity;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.MySQLEntityWithPhoto;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.entities.ReaderEntity;

import java.util.List;

@Entity
@Table(name = "READER_DETAILS")
public class ReaderDetailsEntity extends MySQLEntityWithPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.PERSIST)
    private ReaderEntity reader;

    @Getter
    @Setter
    private ReaderNumberEntity readerNumber;

    @Embedded
    @Getter
    @Setter
    private BirthDateEntity birthDate;

    @Embedded
    @Setter
    @Getter
    private PhoneNumberEntity phoneNumber;

    @Setter
    @Getter
    @Basic
    private boolean gdprConsent;

    @Setter
    @Basic
    @Getter
    private boolean marketingConsent;

    @Setter
    @Basic
    @Getter
    private boolean thirdPartySharingConsent;

    @Version
    @Getter
    @Setter
    private Long version;

    @Getter
    @Setter
    @ManyToMany
    private List<GenreEntity> interestList;

    public ReaderDetailsEntity() {
        // for ORM only
    }
}
