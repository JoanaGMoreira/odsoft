package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories;

import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities.BirthDateEntity;
import pt.psoft.g1.psoftg1.readermanagement.model.BirthDate;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;

public class BirthDateMapper {
    public static BirthDateEntity toEntity(BirthDate birthDate) {
        BirthDateEntity entity = new BirthDateEntity();
        entity.setBirthDate(birthDate.getBirthDate());
        return entity;
    }

    public static BirthDate toModel(BirthDateEntity entity) {
        BirthDate birthDate = new BirthDate();
        birthDate.setBirthDate(entity.getBirthDate());
        return birthDate;
    }
}
