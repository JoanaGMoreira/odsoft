package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class PhoneNumberEntity {
    String phoneNumber;


    public PhoneNumberEntity() {}
}
