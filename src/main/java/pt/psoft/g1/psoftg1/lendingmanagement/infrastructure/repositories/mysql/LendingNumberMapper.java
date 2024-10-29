package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;

import pt.psoft.g1.psoftg1.lendingmanagement.model.LendingNumber;

public class LendingNumberMapper {

        public static LendingNumberEntity toEntity(LendingNumber lendingNumber) {
            return new LendingNumberEntity(
                    lendingNumber.getLendingNumber()
            );
        }

        public static LendingNumber toModel(LendingNumberEntity entity) {
            return new LendingNumber(
                    entity.getLendingNumber()
            );
        }
}
