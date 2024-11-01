package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
public class EmailAddressEntity {
    @Email
    @Getter
    @Setter
    String address;

    public EmailAddressEntity() {}
}
