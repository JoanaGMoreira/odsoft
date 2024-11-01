package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql.IsbnEntity;
import pt.psoft.g1.psoftg1.bookmanagement.model.Isbn;

public class IsbnMapper {
    public static IsbnEntity toEntity(Isbn isbn) {
        IsbnEntity isbnEntity = new IsbnEntity();
        isbnEntity.setIsbn(isbn.getIsbn());
        return isbnEntity;
    }

    public static Isbn toModel(IsbnEntity entity) {
        Isbn isbn = new Isbn();
        isbn.setIsbn(entity.getIsbn());
        return isbn;
    }
}
