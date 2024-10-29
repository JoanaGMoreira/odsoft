package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;

import pt.psoft.g1.psoftg1.lendingmanagement.model.Fine;

public class FineMapper {
    public static FineEntity toEntity(Fine fine) {
        LendingEntity lendingEntity = LendingMapper.toEntity(fine.getLending());
        return new FineEntity(fine.getFineValuePerDayInCents(), fine.getCentsValue(), lendingEntity);
    }

    public static Fine toModel(FineEntity fineEntity) {
        return new Fine(LendingMapper.toModel(fineEntity.getLending()));
    }
}
