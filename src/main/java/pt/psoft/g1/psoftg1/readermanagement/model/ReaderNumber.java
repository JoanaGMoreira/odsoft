package pt.psoft.g1.psoftg1.readermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Getter @Setter
@NoArgsConstructor
public class ReaderNumber implements Serializable {

    private String readerNumber;

    public ReaderNumber(int year, int number) {
        this.readerNumber = year + "/" + number;
    }

    public ReaderNumber(int number) {
        this.readerNumber = LocalDate.now().getYear() + "/" + number;
    }

    public String toString() {
        return this.readerNumber;
    }
}
