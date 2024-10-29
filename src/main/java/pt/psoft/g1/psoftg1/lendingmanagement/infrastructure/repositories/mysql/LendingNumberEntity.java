package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class LendingNumberEntity implements Serializable {

    /**
     * Natural key of a {@code Lending}.
     * The string is constructed based on the values of {@code year} and {@code sequential} (e.g.: 2024/23).
     */
    @Column(name = "LENDING_NUMBER", length = 32)
    @NotNull
    @NotBlank
    @Size(min = 6, max = 32)
    private String lendingNumber;

    /**
     * Constructs a new {@code MySQLLendingNumberEntity} based on a lending number string.
     * @param lendingNumber formatted as "{year}/{sequential}".
     */
    public LendingNumberEntity(String lendingNumber) {
        this.lendingNumber = lendingNumber;
    }

    /**
     * Sets the lending number based on the year and sequential values.
     * @param year        Year component of the lending number.
     * @param sequential  Sequential component of the lending number.
     */
    public void setLendingNumber(int year, int sequential) {
        this.lendingNumber = year + "/" + sequential;
    }

    @Override
    public String toString() {
        return this.lendingNumber;
    }
}
