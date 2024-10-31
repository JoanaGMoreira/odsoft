package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql.BioEntity;
import pt.psoft.g1.psoftg1.authormanagement.model.Bio;


public class BioMapper {
    public static Bio toModel(BioEntity entity) {
        Bio bio = new Bio();
        bio.setBio(entity.getBio());
        return  bio;
    }

    public static BioEntity toEntity(Bio model) {
        BioEntity entity = new BioEntity();
        entity.setBio(model.getBio());
        return entity;
    }
}