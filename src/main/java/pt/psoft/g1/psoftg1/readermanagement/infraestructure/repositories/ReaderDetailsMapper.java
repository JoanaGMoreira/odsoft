package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories;

import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.GenreMapper;
import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.entities.ReaderDetailsEntity;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.PhotoMapper;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.ReaderMapper;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.entities.ReaderEntity;
import pt.psoft.g1.psoftg1.usermanagement.model.Reader;

import java.util.stream.Collectors;

public class ReaderDetailsMapper {
    public static ReaderDetailsEntity toEntity(ReaderDetails readerDetails) {
        ReaderDetailsEntity entity = new ReaderDetailsEntity();
        entity.setReader((ReaderEntity) ReaderMapper.toEntity(readerDetails.getReader()));
        entity.setReaderNumber(ReaderNumberMapper.toEntity(readerDetails.getReaderNumber()));
        entity.setBirthDate(BirthDateMapper.toEntity(readerDetails.getBirthDate()));
        entity.setPhoneNumber(PhoneNumberMapper.toEntity(readerDetails.getPhoneNumber()));
        entity.setGdprConsent(readerDetails.isGdprConsent());
        entity.setMarketingConsent(readerDetails.isMarketingConsent());
        entity.setThirdPartySharingConsent(readerDetails.isThirdPartySharingConsent());
        entity.setVersion(readerDetails.getVersion());
        entity.setInterestList(readerDetails.getInterestList().stream().map(GenreMapper::toEntity).collect(Collectors.toList()));
        entity.setPhoto(readerDetails.getPhoto() != null ? PhotoMapper.toEntity(readerDetails.getPhoto()): null);

        return entity;
    }

    public static ReaderDetails toModel(ReaderDetailsEntity entity) {
        ReaderDetails readerDetails = new ReaderDetails();
        readerDetails.setReader((Reader) ReaderMapper.toModel(entity.getReader()));
        readerDetails.setReaderNumber(ReaderNumberMapper.toModel(entity.getReaderNumber()));
        readerDetails.setBirthDate(BirthDateMapper.toModel(entity.getBirthDate()));
        readerDetails.setPhoneNumber(PhoneNumberMapper.toModel(entity.getPhoneNumber()));
        readerDetails.setGdprConsent(entity.isGdprConsent());
        readerDetails.setMarketingConsent(entity.isMarketingConsent());
        readerDetails.setThirdPartySharingConsent(entity.isThirdPartySharingConsent());
        readerDetails.setVersion(entity.getVersion());
        readerDetails.setInterestList(entity.getInterestList().stream().map(GenreMapper::toModel).collect(Collectors.toList()));
        readerDetails.setPhoto(entity.getPhoto() != null ? PhotoMapper.toModel(entity.getPhoto()): null);
        return readerDetails;
    }

}
