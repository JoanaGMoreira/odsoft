package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.NameMapper;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.entities.ReaderEntity;
import pt.psoft.g1.psoftg1.usermanagement.model.Reader;

public class ReaderMapper extends UserMapper{
    public static ReaderEntity toEntity(Reader reader) {
        ReaderEntity readerEntity = new ReaderEntity();
        readerEntity.setId(reader.getId());
        readerEntity.setCreatedAt(reader.getCreatedAt());
        readerEntity.setEnabled(reader.isEnabled());
        readerEntity.setModifiedAt(reader.getModifiedAt());
        readerEntity.setModifiedBy(reader.getModifiedBy());
        readerEntity.setName(NameMapper.toEntity(reader.getName()));
        readerEntity.setUsername(reader.getUsername());
        readerEntity.setPassword(reader.getPassword());
        readerEntity.setVersion(reader.getVersion());
        return readerEntity;
    }

    public static Reader toModel(ReaderEntity readerEntity) {
        Reader reader = new Reader();
        reader.setId(readerEntity.getId());
        reader.setCreatedAt(readerEntity.getCreatedAt());
        reader.setEnabled(readerEntity.isEnabled());
        reader.setModifiedAt(readerEntity.getModifiedAt());
        reader.setModifiedBy(readerEntity.getModifiedBy());
        reader.setName(NameMapper.toModel(readerEntity.getName()).getName());
        reader.setUsername(readerEntity.getUsername());
        reader.setPassword(readerEntity.getPassword());
        reader.setVersion(readerEntity.getVersion());
        return reader;
    }

}
