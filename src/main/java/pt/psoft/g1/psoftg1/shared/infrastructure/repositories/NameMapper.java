package pt.psoft.g1.psoftg1.shared.infrastructure.repositories;

import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.NameEntity;
import pt.psoft.g1.psoftg1.shared.model.Name;

public class NameMapper {
    public static NameEntity toEntity(Name model) {
        return new NameEntity(model.getName());
    }

    public static Name toModel(NameEntity entity) {
        return new Name(entity.getName());
    }
}
