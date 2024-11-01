package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.AuthorMapper;
import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql.AuthorEntity;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql.BookEntity;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.services.BookCountDTO;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.PhotoMapper;
import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.GenreMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static BookEntity toEntity(Book book) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setIsbn(book.getIsbn() != null ? IsbnMapper.toEntity(book.getIsbn()): null);
        bookEntity.setTitle(book.getTitle() != null ? TittleMapper.toEntity(book.getTitle()): null);
        bookEntity.setDescription(book.getDescription() != null ? DescriptionMapper.toEntity(book.getDescription()): null);
        bookEntity.setVersion(book.getVersion());
        bookEntity.setGenre( book.getGenre() != null ? GenreMapper.toEntity(book.getGenre()): null);

        if(!book.getAuthors().isEmpty()) {
            List<AuthorEntity> authors = book.getAuthors().stream()
                    .map(AuthorMapper::toEntity)
                    .collect(Collectors.toList());
            bookEntity.setAuthors(authors);
        }

        bookEntity.setPhoto(book.getPhoto() != null ?  PhotoMapper.toEntity(book.getPhoto()): null);
        return bookEntity;
    }

    public static Book toModel(BookEntity bookEntity) {
        Book book = new Book();
        book.setIsbn(bookEntity.getIsbn() != null ? IsbnMapper.toModel(bookEntity.getIsbn()): null);
        book.setTitle(bookEntity.getTitle() != null ? TittleMapper.toModel(bookEntity.getTitle()): null);
        book.setDescription(bookEntity.getDescription() != null ? DescriptionMapper.toModel(bookEntity.getDescription()): null);
        book.setVersion(bookEntity.getVersion());
        book.setGenre( bookEntity.getGenre() != null ? GenreMapper.toModel(bookEntity.getGenre()): null);

        if(!bookEntity.getAuthors().isEmpty()) {
            List<Author> authors = bookEntity.getAuthors().stream()
                    .map(AuthorMapper::toModel)
                    .collect(Collectors.toList());
            book.setAuthors(authors);
        }

        book.setPhoto(bookEntity.getPhoto() != null ?  PhotoMapper.toModel(bookEntity.getPhoto()): null);
        return book;
    }

    public static BookCountDTO toBookEntityCountDTO(BookEntity bookEntity, Long count) {
        return new BookCountDTO(toModel(bookEntity), count);
    }
}
