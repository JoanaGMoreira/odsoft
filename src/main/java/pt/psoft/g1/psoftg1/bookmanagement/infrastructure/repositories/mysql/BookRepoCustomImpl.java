package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql.AuthorEntity;
import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.BookMapper;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.repositories.BookRepository;
import pt.psoft.g1.psoftg1.bookmanagement.services.BookCountDTO;
import pt.psoft.g1.psoftg1.bookmanagement.services.SearchBooksQuery;
import org.springframework.data.domain.Page;
import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mysql.GenreEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("h2")
class BookRepoCustomImpl implements BookRepository {
    // get the underlying JPA Entity Manager via spring thru constructor dependency
    // injection
    private final EntityManager em;

    private final MySQLBookRepository mysqlRepository;

    @Autowired
    public BookRepoCustomImpl(EntityManager em, @Lazy MySQLBookRepository mysqlRepository) {
        this.em = em;
        this.mysqlRepository = mysqlRepository;
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return mysqlRepository.findByGenre(genre).stream().map(BookMapper::toModel).toList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return mysqlRepository.findByTitle(title).stream().map(BookMapper::toModel).toList();
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return mysqlRepository.findByAuthorName(authorName).stream().map(BookMapper::toModel).toList();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return mysqlRepository.findByIsbn(isbn).map(BookMapper::toModel);
    }

    @Override
    public Page<BookCountDTO> findTop5BooksLent(LocalDate oneYearAgo, Pageable pageable) {
        return null;
    }

//    @Override
//    public Page<BookCountDTO> findTop5BooksLent(LocalDate oneYearAgo, Pageable pageable) {
//        return mysqlRepository.findTop5BooksLent(oneYearAgo, pageable);
//    }

    @Override
    public List<Book> findBooksByAuthorNumber(Long authorNumber) {
        return mysqlRepository.findBooksByAuthorNumber(authorNumber).stream().map(BookMapper::toModel).toList();
    }

    @Override
    public List<Book> searchBooks(pt.psoft.g1.psoftg1.shared.services.Page page, SearchBooksQuery query) {
        String title = query.getTitle();
        String genre = query.getGenre();
        String authorName = query.getAuthorName();

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
        final Root<BookEntity> root = cq.from(BookEntity.class);
        final Join<BookEntity, GenreEntity> genreJoin = root.join("genre");
        final Join<BookEntity, AuthorEntity> authorJoin = root.join("authors");
        cq.select(root);

        final List<Predicate> where = new ArrayList<>();

        if (StringUtils.hasText(title))
            where.add(cb.like(root.get("title").get("title"), title + "%"));

        if (StringUtils.hasText(genre))
            where.add(cb.like(genreJoin.get("genre"), genre + "%"));

        if (StringUtils.hasText(authorName))
            where.add(cb.like(authorJoin.get("name").get("name"), authorName + "%"));

        cq.where(where.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("title"))); // Order by title, alphabetically

        final TypedQuery<BookEntity> q = em.createQuery(cq);
        q.setFirstResult((page.getNumber() - 1) * page.getLimit());
        q.setMaxResults(page.getLimit());

        return q.getResultList().stream().map(BookMapper::toModel).toList();
    }

    @Override
    public Book save(Book book) {
        return BookMapper.toModel(mysqlRepository.save(BookMapper.toEntity(book)));
    }

    @Override
    public void delete(Book book) {
        mysqlRepository.delete(BookMapper.toEntity(book));
    }


}
