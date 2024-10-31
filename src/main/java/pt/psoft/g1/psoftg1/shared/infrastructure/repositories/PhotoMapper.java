package pt.psoft.g1.psoftg1.shared.infrastructure.repositories;

import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.PhotoEntity;
import pt.psoft.g1.psoftg1.shared.model.Photo;

public class PhotoMapper {
    public static Photo toModel(PhotoEntity entity) {
        Photo photo = new Photo();
        photo.setPhotoFile(entity.getPhotoFile());
        return photo;
    }

    public static PhotoEntity toEntity(Photo model) {
        PhotoEntity entity = new PhotoEntity();
        entity.setPhotoFile(model.getPhotoFile());
        return entity;
    }
}
