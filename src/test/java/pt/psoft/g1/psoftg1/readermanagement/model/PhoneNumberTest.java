package pt.psoft.g1.psoftg1.readermanagement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberTest {
    @Test
    void ensureValidMobilePhoneNumberIsAccepted() {
        assertDoesNotThrow(() -> new PhoneNumber("912345678"));
    }

    @Test
    void ensureValidFixedPhoneNumberIsAccepted() {
        assertDoesNotThrow(() -> new PhoneNumber("212345678"));
    }

    @Test
    void ensureInvalidPhoneNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("12345678")); // Too short
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("00123456789")); // Too long
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("abcdefghij")); // Non-numeric
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("512345678")); // Invalid start digit
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("91234567")); // Too short by one digit
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("21234567")); // Too short by one digit
    }

    @Test
    void ensureCorrectStringRepresentation() {
        PhoneNumber phoneNumber = new PhoneNumber("912345678");
        assertEquals("912345678", phoneNumber.toString());

        PhoneNumber anotherPhoneNumber = new PhoneNumber("212345678");
        assertEquals("212345678", anotherPhoneNumber.toString());
    }

    // Testes transparentes
    @Test
    void ensureSetPhoneNumberCorrectlyValidatesStartDigitAndLength() {
        PhoneNumber validPhoneNumber1 = new PhoneNumber("912345678");
        assertEquals("912345678", validPhoneNumber1.toString());

        PhoneNumber validPhoneNumber2 = new PhoneNumber("212345678");
        assertEquals("212345678", validPhoneNumber2.toString());


        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("312345678"));

        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("9123456"));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("9123456789"));
    }


    @Test
    void ensureBoundaryValuesAreHandledCorrectly() {
        assertDoesNotThrow(() -> new PhoneNumber("900000000")); // Caso válido mínimo para 9
        assertDoesNotThrow(() -> new PhoneNumber("299999999")); // Caso válido máximo para 2
    }


    @Test
    void ensurePhoneNumberRejectsAlphaNumericInput() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("91a456b78"));
    }
}
