package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.services.SearchBooksQuery;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
class BookRepoCustomImpl implements BookRepoCustom {
    // get the underlying JPA Entity Manager via spring thru constructor dependency
    // injection
    private final EntityManager em;

    @Override
    public List<Book> searchBooks(pt.psoft.g1.psoftg1.shared.services.Page page, SearchBooksQuery query) {
        String title = query.getTitle();
        String genre = query.getGenre();
        String authorName = query.getAuthorName();

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        final Root<Book> root = cq.from(Book.class);
        final Join<Book, Genre> genreJoin = root.join("genre");
        final Join<Book, Author> authorJoin = root.join("authors");
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

        final TypedQuery<Book> q = em.createQuery(cq);
        q.setFirstResult((page.getNumber() - 1) * page.getLimit());
        q.setMaxResults(page.getLimit());

        return q.getResultList();
    }
}
