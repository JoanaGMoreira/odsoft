package pt.psoft.g1.psoftg1.readermanagement.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class EmailAddress {
    @Email
    @Getter
    @Setter
    String address;
}
