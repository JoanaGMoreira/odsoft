package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
@Getter @Setter
public class ReaderNumberEntity implements Serializable {
    @Column(name = "READER_NUMBER")
    private String readerNumber;

    public ReaderNumberEntity() {}
}
