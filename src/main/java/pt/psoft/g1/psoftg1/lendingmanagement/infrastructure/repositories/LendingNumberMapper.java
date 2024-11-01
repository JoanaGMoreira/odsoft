package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.entities.LendingNumberEntity;
import pt.psoft.g1.psoftg1.lendingmanagement.model.LendingNumber;

public class LendingNumberMapper {

        public static LendingNumberEntity toEntity(LendingNumber lendingNumber) {
            LendingNumberEntity entity = new LendingNumberEntity();
            entity.setLendingNumber(lendingNumber.getLendingNumber());
            return entity;
        }

        public static LendingNumber toModel(LendingNumberEntity entity) {
            return new LendingNumber(
                    entity.getLendingNumber()
            );
        }
}
