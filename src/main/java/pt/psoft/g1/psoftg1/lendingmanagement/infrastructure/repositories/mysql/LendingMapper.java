package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;

public class LendingMapper {

    public static LendingEntity toEntity(Lending lending) {
        return new LendingEntity(
                lending.getBook(),
                lending.getReaderDetails(),
                lending.getLendingNumber(),
                lending.getStartDate(),
                lending.getLimitDate(),
                lending.getFineValuePerDayInCents()
        );
    }

    public static Lending toModel(LendingEntity entity) {
        Lending lending = new Lending(
                entity.getBook(),
                entity.getReaderDetails(),
                Integer.valueOf(entity.getLendingNumber().getLendingNumber()),
                entity.getStartDate(),
                entity.getLimitDate(),
                entity.getFineValuePerDayInCents()
        );
        lending.setCommentary(entity.getCommentary());
        if (entity.getReturnedDate() != null) {
            lending.returnBook(entity.getCommentary());
        }
        return lending;
    }
}
