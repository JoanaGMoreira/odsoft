package pt.psoft.g1.psoftg1.lendingmanagement.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import pt.psoft.g1.psoftg1.usermanagement.model.Reader;
import org.hibernate.StaleObjectStateException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@PropertySource({"classpath:config/library.properties"})
class LendingTest {
    private static final ArrayList<Author> authors = new ArrayList<>();
    private static Book book;
    private static ReaderDetails readerDetails;
    @Value("${lendingDurationInDays}")
    private int lendingDurationInDays;
    @Value("${fineValuePerDayInCents}")
    private int fineValuePerDayInCents;

    @BeforeAll
    public static void setup(){
        Author author = new Author("Manuel Antonio Pina",
                "Manuel António Pina foi um jornalista e escritor português, premiado em 2011 com o Prémio Camões",
                null);
        authors.add(author);
        book = new Book("9782826012092",
                "O Inspetor Max",
                "conhecido pastor-alemão que trabalha para a Judiciária...",
                new Genre("Romance"),
                authors,
                null);
        readerDetails = new ReaderDetails(1,
                Reader.newReader("manuel@gmail.com", "Manuelino123!", "Manuel Sarapinto das Coives"),
                "2000-01-01",
                "919191919",
                true,
                true,
                true,
                null,
                null);
    }

    /** TESTES DE CAIXA PRETA **/

    @Test
    void ensureBookNotNull(){
        assertThrows(IllegalArgumentException.class, () ->
                new Lending(null, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents));
    }

    @Test
    void ensureReaderNotNull(){
        assertThrows(IllegalArgumentException.class, () ->
                new Lending(book, null, 1, lendingDurationInDays, fineValuePerDayInCents));
    }

    @Test
    void ensureValidReaderNumber(){
        assertThrows(IllegalArgumentException.class, () ->
                new Lending(book, readerDetails, -1, lendingDurationInDays, fineValuePerDayInCents));
    }

    @Test
    void testFineCalculationWithDelay() {
        Lending lending = new Lending(book, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents);
        lending.setReturned(0, null);
        lending.setLimitDate(LocalDate.now().minusDays(3)); // Simula atraso de 3 dias
        assertEquals(Optional.of(3 * fineValuePerDayInCents), lending.getFineValueInCents());
    }

    @Test
    void testGetDaysUntilReturn(){
        Lending lending = new Lending(book, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents);
        assertEquals(Optional.of(lendingDurationInDays), lending.getDaysUntilReturn());
    }

    @Test
    void testGetTitle() {
        Lending lending = new Lending(book, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents);
        assertEquals("O Inspetor Max", lending.getTitle());
    }

    @Test
    void testGetLendingNumber() {
        Lending lending = new Lending(book, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents);
        assertEquals(LocalDate.now().getYear() + "/1", lending.getLendingNumber());
    }


    /** TESTES DE CAIXA BRANCA **/

    @Test
    void testGetDaysDelayed(){
        Lending lending = new Lending(book, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents);
        lending.setLimitDate(LocalDate.now().minusDays(2));
        lending.setReturned(0, null);
        assertEquals(2, lending.getDaysDelayed());
    }

    @Test
    void testSetReturnedWithVersionControl() {
        Lending lending = new Lending(book, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents);
        lending.setVersion(1);
        assertThrows(StaleObjectStateException.class, () -> lending.setReturned(0, null));
    }

    @Test
    void testBootstrappingLendingCreation() {
        Lending lending = Lending.newBootstrappingLending(
                book, readerDetails, LocalDate.now().getYear(), 1,
                LocalDate.now().minusDays(10), LocalDate.now().minusDays(2),
                lendingDurationInDays, fineValuePerDayInCents);

        assertEquals(LocalDate.now().minusDays(10), lending.getStartDate());
        assertEquals(LocalDate.now().minusDays(2), lending.getReturnedDate());
        assertEquals(LocalDate.now().minusDays(10).plusDays(lendingDurationInDays), lending.getLimitDate());
    }

    @Test
    void testGetDaysUntilReturnBeforeDueDate() {
        Lending lending = new Lending(book, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents);
        lending.setLimitDate(LocalDate.now().plusDays(5)); // Set 5 days until return
        assertEquals(Optional.of(5), lending.getDaysUntilReturn());
    }

    @Test
    void testGetDaysOverdueAfterLimitDate() {
        Lending lending = new Lending(book, readerDetails, 1, lendingDurationInDays, fineValuePerDayInCents);
        lending.setLimitDate(LocalDate.now().minusDays(4)); // Set overdue by 4 days
        assertEquals(Optional.of(4), lending.getDaysOverdue());
    }
}
