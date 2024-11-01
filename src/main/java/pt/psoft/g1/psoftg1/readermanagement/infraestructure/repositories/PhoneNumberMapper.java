package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories;

import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities.PhoneNumberEntity;
import pt.psoft.g1.psoftg1.readermanagement.model.PhoneNumber;

public class PhoneNumberMapper {
    public static PhoneNumberEntity toEntity(PhoneNumber phoneNumber) {
        PhoneNumberEntity entity = new PhoneNumberEntity();
        entity.setPhoneNumber(phoneNumber.getPhoneNumber());
        return entity;
    }
    public static PhoneNumber toModel(PhoneNumberEntity entity) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber(entity.getPhoneNumber());
        return phoneNumber;
    }
}
