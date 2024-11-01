package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories;

import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities.ReaderNumberEntity;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderNumber;

public class ReaderNumberMapper {
    public static ReaderNumberEntity toEntity(ReaderNumber readerNumber) {
        ReaderNumberEntity entity = new ReaderNumberEntity();
        entity.setReaderNumber(readerNumber.getReaderNumber());
        return entity;
    }

    public static ReaderNumber toModel(ReaderNumberEntity entity) {
        ReaderNumber readerNumber = new ReaderNumber();
        readerNumber.setReaderNumber(entity.getReaderNumber());
        return readerNumber;
    }
}
