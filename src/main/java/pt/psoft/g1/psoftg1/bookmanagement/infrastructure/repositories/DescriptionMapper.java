package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql.DescriptionEntity;
import pt.psoft.g1.psoftg1.bookmanagement.model.Description;

public class DescriptionMapper {

    public static DescriptionEntity toEntity(Description description) {

        return new DescriptionEntity(description.getDescription());
    }

    public static Description toModel(DescriptionEntity entity) {
        return new Description(entity.getDescription());
    }
}
