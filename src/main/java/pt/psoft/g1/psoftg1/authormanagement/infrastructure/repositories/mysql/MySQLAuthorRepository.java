package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.psoft.g1.psoftg1.authormanagement.api.AuthorLendingView;

import java.util.List;
import java.util.Optional;

//@Repository
@Profile("mysql")
public interface MySQLAuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByAuthorNumber(Long authorNumber);

    @Query("SELECT new pt.psoft.g1.psoftg1.authormanagement.api.AuthorLendingView(a.name.name, COUNT(l.pk)) " +
            "FROM BookEntity b " +
            "JOIN b.authors a " +
            "JOIN LendingEntity l ON l.book.pk = b.pk " +
            "GROUP BY a.name " +
            "ORDER BY COUNT(l) DESC")
    Page<AuthorLendingView> findTopAuthorByLendings(Pageable pageable);

    @Query("SELECT DISTINCT coAuthor FROM BookEntity b " +
            "JOIN b.authors coAuthor " +
            "WHERE b IN (SELECT b FROM BookEntity b JOIN b.authors a WHERE a.authorNumber = :authorNumber) " +
            "AND coAuthor.authorNumber <> :authorNumber")
    List<AuthorEntity> findCoAuthorsByAuthorNumber(Long authorNumber);
}

