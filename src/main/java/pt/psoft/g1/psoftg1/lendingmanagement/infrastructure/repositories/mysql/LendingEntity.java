package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.lendingmanagement.model.LendingNumber;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "lending", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"LENDING_NUMBER"})
})
public class LendingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Embedded
    private LendingNumber lendingNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Book book;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ReaderDetails readerDetails;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDate startDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate limitDate;

    @Column
    private LocalDate returnedDate;

    @Version
    private long version;

    @Column(length = 1024)
    private String commentary;

    private int fineValuePerDayInCents;

    // Constructor
    protected LendingEntity() {}

    public LendingEntity(Book book, ReaderDetails readerDetails, LendingNumber lendingNumber,
                         LocalDate startDate, LocalDate limitDate, int fineValuePerDayInCents) {
        this.book = book;
        this.readerDetails = readerDetails;
        this.lendingNumber = lendingNumber;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.fineValuePerDayInCents = fineValuePerDayInCents;
    }

    // Additional methods for managing fields or JPA-only logic can go here
}
