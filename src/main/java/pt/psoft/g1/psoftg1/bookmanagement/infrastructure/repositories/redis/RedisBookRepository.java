package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.repositories.BookRepository;
import pt.psoft.g1.psoftg1.bookmanagement.services.BookCountDTO;
import pt.psoft.g1.psoftg1.bookmanagement.services.SearchBooksQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("redis")
public class RedisBookRepository implements BookRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisBookRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return List.of();
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return List.of();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public Page<BookCountDTO> findTop5BooksLent(LocalDate oneYearAgo, Pageable pageable) {
        return null;
    }

    @Override
    public List<Book> findBooksByAuthorNumber(Long authorNumber) {
        return List.of();
    }

    @Override
    public List<Book> searchBooks(pt.psoft.g1.psoftg1.shared.services.Page page, SearchBooksQuery query) {
        return List.of();
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }
}
