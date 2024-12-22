package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.entities.LendingEntity;

import java.util.*;

//@Repository
//@Profile("!mysql")
public interface MySQLLendingRepository extends JpaRepository<LendingEntity, Long> {

    @Query("SELECT l " +
            "FROM LendingEntity l " +
            "WHERE l.lendingNumber.lendingNumber = :lendingNumber")
    Optional<LendingEntity> findByLendingNumber(String lendingNumber);

    //http://www.h2database.com/html/commands.html

    @Query("SELECT l " +
            "FROM LendingEntity l " +
            "JOIN BookEntity b ON l.book.pk = b.pk " +
            "JOIN ReaderDetailsEntity r ON l.readerDetails.pk = r.pk " +
            "WHERE b.isbn.isbn = :isbn " +
            "AND r.readerNumber.readerNumber = :readerNumber ")
    List<LendingEntity> listByReaderNumberAndIsbn(String readerNumber, String isbn);

    @Query("SELECT COUNT (l) " +
            "FROM LendingEntity l " +
            "WHERE YEAR(l.startDate) = YEAR(CURRENT_DATE)")
    int getCountFromCurrentYear();

    @Query("SELECT l " +
            "FROM LendingEntity l " +
                "JOIN ReaderDetailsEntity r ON l.readerDetails.pk = r.pk " +
            "WHERE r.readerNumber.readerNumber = :readerNumber " +
                "AND l.returnedDate IS NULL")
    List<LendingEntity> listOutstandingByReaderNumber(@Param("readerNumber") String readerNumber);

    @Query(value =
            "SELECT AVG(DATEDIFF(day, l.start_date, l.returned_date)) " +
            "FROM LendingEntity l"
            , nativeQuery = true)
    Double getAverageDuration();

    @Query(value =
            "SELECT AVG(DATEDIFF(day, l.start_date, l.returned_date)) " +
                    "FROM LendingEntity l " +
                    "JOIN BookEntity b ON l.BOOK_PK = b.PK " +
                    "WHERE b.ISBN = :isbn"
            , nativeQuery = true)
    Double getAvgLendingDurationByIsbn(@Param("isbn") String isbn);


}
