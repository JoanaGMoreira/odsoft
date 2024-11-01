package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories;

import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities.EmailAddressEntity;
import pt.psoft.g1.psoftg1.readermanagement.model.EmailAddress;

public class EmailAddressMapper {
    public static EmailAddressEntity toEntity(EmailAddress emailAddress) {
        EmailAddressEntity entity = new EmailAddressEntity();
        entity.setAddress(emailAddress.getAddress());
        return entity;
    }
     public static EmailAddress  toModel(EmailAddressEntity entity) {
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setAddress(entity.getAddress());
        return emailAddress;
    }
}
