package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.entities.FineEntity;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Fine;

public class FineMapper {
    public static FineEntity toEntity(Fine fine) {
        FineEntity fineEntity = new FineEntity();
        fineEntity.setCentsValue(fine.getCentsValue());
        fineEntity.setFineValuePerDayInCents(fine.getFineValuePerDayInCents());
        fineEntity.setLending(LendingMapper.toEntity(fine.getLending()));
        return fineEntity;
    }

    public static Fine toModel(FineEntity fineEntity) {
        return new Fine(LendingMapper.toModel(fineEntity.getLending()));
    }
}
