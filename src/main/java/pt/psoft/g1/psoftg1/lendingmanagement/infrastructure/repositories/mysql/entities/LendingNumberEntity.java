package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.entities;
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
    //@Column(name = "LENDING_NUMBER", length = 32)
    @NotNull
    @NotBlank
    @Size(min = 6, max = 32)
    private String lendingNumber;

    @Override
    public String toString() {
        return this.lendingNumber;
    }
}
