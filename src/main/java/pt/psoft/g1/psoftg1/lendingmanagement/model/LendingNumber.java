package pt.psoft.g1.psoftg1.lendingmanagement.model;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class LendingNumber implements Serializable {

    private String lendingNumber;

    /**
     * Constructs a new {@code LendingNumber} based on a year and a sequential number.
     * @param year       Year component of the lending number.
     * @param sequential Sequential component of the lending number.
     */
    public LendingNumber(int year, int sequential) {
        if (year < 1970 || LocalDate.now().getYear() < year)
            throw new IllegalArgumentException("Invalid year component");
        if (sequential < 0)
            throw new IllegalArgumentException("Sequential component cannot be negative");
        this.lendingNumber = year + "/" + sequential;
    }

    /**
     * Constructs a new {@code LendingNumber} based on a lending number string.
     * @param lendingNumber Formatted lending number as "{year}/{sequential}".
     */
    public LendingNumber(String lendingNumber) {
        if (lendingNumber == null || !lendingNumber.matches("\\d{4}/\\d+"))
            throw new IllegalArgumentException("Lending number format should be \"{year}/{sequential}\"");
        this.lendingNumber = lendingNumber;
    }

    /**
     * Constructs a new {@code LendingNumber} object based on a given sequential number.
     * <p>
     * The {@code sequential} value should be retrieved from the count of lendings made in the current year.
     * The {@code year} value is automatically set with {@code LocalDate.now().getYear()}.
     * @param sequential Sequential component of the {@code LendingNumber}
     * */
    public  LendingNumber(int sequential) {
        if(sequential < 0)
            throw new IllegalArgumentException("Sequencial component cannot be negative");
        this.lendingNumber = LocalDate.now().getYear() + "/" + sequential;
    }

    @Override
    public String toString() {
        return this.lendingNumber;
    }
}
