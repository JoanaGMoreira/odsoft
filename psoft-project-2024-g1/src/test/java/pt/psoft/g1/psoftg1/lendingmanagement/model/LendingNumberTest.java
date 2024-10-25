package pt.psoft.g1.psoftg1.lendingmanagement.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LendingNumberTest {

    // Caixa Opaca
    @Test
    void ensureLendingNumberNotNull(){
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber(null));
    }

    @Test
    void ensureLendingNumberNotBlank(){
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber(""));
    }

    @Test
    void ensureLendingNumberNotWrongFormat(){
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber("1/2024"));
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber("24/1"));
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber("2024-1"));
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber("2024\\1"));
    }

    @Test
    void ensureLendingNumberIsSetWithString() {
        final var ln = new LendingNumber("2024/1");
        assertEquals("2024/1", ln.toString());
    }

    @Test
    void ensureLendingNumberIsSetWithSequential() {
        final LendingNumber ln = new LendingNumber(1);
        assertNotNull(ln);
        assertEquals(LocalDate.now().getYear() + "/1", ln.toString());
    }

    @Test
    void ensureLendingNumberIsSetWithYearAndSequential() {
        final LendingNumber ln = new LendingNumber(2024,1);
        assertNotNull(ln);
        assertEquals("2024/1", ln.toString());
    }

    @Test
    void ensureSequentialCannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber(2024,-1));
    }

    @Test
    void ensureYearCannotBeInTheFuture() {
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber(LocalDate.now().getYear()+1,1));
    }

    // Caixa Transparente
    @Test
    void ensureYearBelow1970IsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber(1969, 1), "Year should not be below 1970");
    }

    @Test
    void ensureYearAndSequentialFormatsAreCorrect() {
        LendingNumber ln1 = new LendingNumber(2024, 15);
        assertEquals("2024/15", ln1.toString(), "Expected format: '2024/15'");

        LendingNumber ln2 = new LendingNumber("2023/10");
        assertEquals("2023/10", ln2.toString(), "Expected format: '2023/10'");
    }

    @Test
    void ensureORMConstructorWorksCorrectly() {
        LendingNumber ln = new LendingNumber();
        assertNull(ln.toString(), "Default constructor should initialize lendingNumber to null for ORM use");
    }

    @Test
    void ensureMinimumStringLength() {
        assertThrows(IllegalArgumentException.class, () -> new LendingNumber("20/1"), "Lending number should have a minimum length of 6 characters");
    }

    @Test
    void ensureSequentialZeroIsAllowed() {
        LendingNumber ln = new LendingNumber(2024, 0);
        assertEquals("2024/0", ln.toString(), "Sequential value '0' should be allowed");
    }

    @Test
    void ensureMaximumStringLength() {
        String longNumber = "2024/" + "9".repeat(35);
        assertDoesNotThrow(() -> new LendingNumber(longNumber), "Lending number up to 32 characters should be allowed");
    }
}
