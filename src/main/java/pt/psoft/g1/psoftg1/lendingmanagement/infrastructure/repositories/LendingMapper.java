package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories;
import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.BookMapper;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.entities.LendingEntity;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.ReaderDetailsMapper;

public class LendingMapper {

    public static LendingEntity toEntity(Lending lending) {
        LendingEntity entity = new LendingEntity();
        entity.setBook(BookMapper.toEntity(lending.getBook()));
        entity.setReaderDetails(ReaderDetailsMapper.toEntity(lending.getReaderDetails()));
        entity.setLendingNumber(LendingNumberMapper.toEntity(lending.getLendingNumber()));
        entity.setStartDate(lending.getStartDate());
        entity.setLimitDate(lending.getLimitDate());
        entity.setFineValuePerDayInCents(lending.getFineValuePerDayInCents());
        entity.setCommentary(lending.getCommentary());
        entity.setReturnedDate(lending.getReturnedDate());
        return entity;
    }

    public static Lending toModel(LendingEntity entity) {
        Lending lending = new Lending();
        lending.setBook(BookMapper.toModel(entity.getBook()));
        lending.setReaderDetails(ReaderDetailsMapper.toModel(entity.getReaderDetails()));
        lending.setLendingNumber(LendingNumberMapper.toModel(entity.getLendingNumber()));
        lending.setStartDate(entity.getStartDate());
        lending.setLimitDate(entity.getLimitDate());
        lending.setFineValuePerDayInCents(entity.getFineValuePerDayInCents());
        lending.setCommentary(entity.getCommentary());
        if (entity.getReturnedDate() != null) {
            lending.setReturnedDate(entity.getReturnedDate());
        }
        return lending;
    }
}
