package pt.psoft.g1.psoftg1.readermanagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BirthDateTest {

    @Test
    void ensureBirthDateCanBeCreatedWithValidDate() {
        assertDoesNotThrow(() -> new BirthDate(2000, 1, 1));
    }

    @Test
    void ensureBirthDateCanBeCreatedWithValidStringDate() {
        assertDoesNotThrow(() -> new BirthDate("2000-01-01"));
    }

    @Test
    void ensureExceptionIsThrownForInvalidStringDateFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new BirthDate("01-01-2000"));
        assertEquals("Provided birth date is not in a valid format. Use yyyy-MM-dd", exception.getMessage());
    }

    @Test
    void ensureCorrectStringRepresentation() {
        BirthDate birthDate = new BirthDate(2000, 1, 1);
        assertEquals("2000-1-1", birthDate.toString());
    }

    @Test
    void ensureExceptionForInvalidFormatInConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new BirthDate("2020-31-12"));
        assertEquals("Provided birth date is not in a valid format. Use yyyy-MM-dd", exception.getMessage());
    }

    // Testes de Caixa Transparente

    // Configurações iniciais para modificar os valores de campo privados através de reflexão
    private BirthDate birthDate;
    private final int VALID_YEAR = 2000;
    private final int VALID_MONTH = 1;
    private final int VALID_DAY = 1;

    @BeforeEach
    void setUp() {
        birthDate = new BirthDate(VALID_YEAR, VALID_MONTH, VALID_DAY);
    }

    @Test
    void ensureMinimumAgeCheckApplies() {
        // Ajustar idade mínima para 18 e criar data que falhe na validação
        ReflectionTestUtils.setField(birthDate, "minimumAge", 18);

        LocalDate invalidBirthDate = LocalDate.now().minusYears(17);
        int year = invalidBirthDate.getYear();
        int month = invalidBirthDate.getMonthValue();
        int day = invalidBirthDate.getDayOfMonth();

        AccessDeniedException exception = assertThrows(AccessDeniedException.class, () -> new BirthDate(year, month, day));
        assertEquals("User must be, at least, 18 years old", exception.getMessage());
    }

    @Test
    void ensureMinimumAgeCheckDoesNotApplyForOlderAge() {
        ReflectionTestUtils.setField(birthDate, "minimumAge", 18);

        LocalDate validBirthDate = LocalDate.now().minusYears(20);
        int year = validBirthDate.getYear();
        int month = validBirthDate.getMonthValue();
        int day = validBirthDate.getDayOfMonth();

        assertDoesNotThrow(() -> new BirthDate(year, month, day));
    }

    @Test
    void ensureDateFormatPatternIsAppliedCorrectly() {
        String pattern = (String) ReflectionTestUtils.getField(birthDate, "dateFormatRegexPattern");
        assertEquals("\\d{4}-\\d{2}-\\d{2}", pattern, "Date format pattern mismatch");
    }

    @Test
    void ensureMinimumAgeIsSetToExpectedValue() {
        // Define um valor mínimo para a idade e valida se está ajustado corretamente
        ReflectionTestUtils.setField(birthDate, "minimumAge", 16);
        int minimumAge = (int) ReflectionTestUtils.getField(birthDate, "minimumAge");
        assertEquals(16, minimumAge, "Minimum age should be 16");
    }

    // Verifica o construtor string, se ele manipula o regex e a inicialização de valores corretamente
    @Test
    void ensureConstructorStringInitializesDateCorrectly() {
        BirthDate birthDateFromString = new BirthDate("2000-01-01");
        LocalDate expectedDate = LocalDate.of(2000, 1, 1);
        assertEquals(expectedDate, birthDateFromString.getBirthDate(), "BirthDate initialized incorrectly from string");
    }

    @Test
    void ensureToStringReturnsCorrectFormat() {
        ReflectionTestUtils.setField(birthDate, "birthDate", LocalDate.of(2000, 1, 1));
        assertEquals("2000-1-1", birthDate.toString(), "toString does not match expected format");
    }

}
