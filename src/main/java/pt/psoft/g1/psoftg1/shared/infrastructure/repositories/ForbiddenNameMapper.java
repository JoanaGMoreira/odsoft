package pt.psoft.g1.psoftg1.shared.infrastructure.repositories;

import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.ForbiddenNameEntity;
import pt.psoft.g1.psoftg1.shared.model.ForbiddenName;

public class ForbiddenNameMapper{
    // Convert Service Model to MySQL Entity
    public static ForbiddenNameEntity toMySQLEntity(ForbiddenName serviceModel) {
        if (serviceModel == null) return null;
        return new ForbiddenNameEntity(serviceModel.getForbiddenName());
    }

    // Convert MySQL Entity to Service Model
    public static ForbiddenName toServiceModelFromMySQLEntity(ForbiddenNameEntity mySQLEntity) {
        if (mySQLEntity == null) return null;
        return new ForbiddenName(mySQLEntity.getForbiddenName());
    }
}
