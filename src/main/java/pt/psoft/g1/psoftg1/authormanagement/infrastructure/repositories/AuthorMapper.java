package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql.AuthorEntity;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.NameMapper;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.PhotoMapper;


public class AuthorMapper {
    public static Author toModel(AuthorEntity entity) {
        Author author = new Author();
        author.setAuthorNumber(entity.getAuthorNumber());
        author.setName(entity.getName() != null ? NameMapper.toModel(entity.getName()): null);
        author.setBio(entity.getBio() != null ? BioMapper.toModel(entity.getBio()): null);
        author.setPhoto(entity.getPhoto() != null ? PhotoMapper.toModel(entity.getPhoto()): null);
        return author;
    }

    public static AuthorEntity toEntity(Author model) {
        AuthorEntity entity = new AuthorEntity();
        entity.setAuthorNumber(model.getAuthorNumber());
        entity.setName(NameMapper.toEntity(model.getName()));
        entity.setBio(BioMapper.toEntity(model.getBio()));
        entity.setPhoto(model.getPhoto() != null ?  PhotoMapper.toEntity(model.getPhoto()): null);
        return entity;
    }
}
