package pt.psoft.g1.psoftg1.lendingmanagement.model;

import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Getter
public class Lending {

    private final LendingNumber lendingNumber;
    private final Book book;
    private final ReaderDetails readerDetails;
    private final LocalDate startDate;
    private final LocalDate limitDate;
    private LocalDate returnedDate;
    private final int fineValuePerDayInCents;

    @Setter
    private String commentary;

    public Lending(Book book, ReaderDetails readerDetails, int seq, LocalDate startDate,
                   LocalDate limitDate, int fineValuePerDayInCents) {
        this.book = book;
        this.readerDetails = readerDetails;
        this.startDate = startDate;
        this.limitDate = limitDate;
        this.lendingNumber = new LendingNumber(seq);
        this.fineValuePerDayInCents = fineValuePerDayInCents;
    }

    public void returnBook(String commentary) {
        if (this.returnedDate != null)
            throw new IllegalArgumentException("Book has already been returned!");
        this.returnedDate = LocalDate.now();
        this.commentary = commentary;
    }

    public int getDaysDelayed() {
        LocalDate dateToCheck = (this.returnedDate != null) ? this.returnedDate : LocalDate.now();
        return (int) ChronoUnit.DAYS.between(limitDate, dateToCheck);
    }

    public Optional<Integer> calculateFine() {
        int daysDelayed = getDaysDelayed();
        return daysDelayed > 0 ? Optional.of(daysDelayed * fineValuePerDayInCents) : Optional.empty();
    }
}
