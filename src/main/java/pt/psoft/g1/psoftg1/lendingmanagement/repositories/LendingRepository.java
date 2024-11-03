package pt.psoft.g1.psoftg1.lendingmanagement.repositories;

import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.shared.services.Page;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LendingRepository {
    Optional<Lending> findByLendingNumber(String lendingNumber);
    List<Lending> listByReaderNumberAndIsbn(String readerNumber, String isbn);
    int getCountFromCurrentYear();
    List<Lending> listOutstandingByReaderNumber(String readerNumber);
    Double getAverageDuration();
    Double getAvgLendingDurationByIsbn(String isbn);


    List<Lending> getOverdue(Page page);
    List<Lending> searchLendings(Page page, String readerNumber, String isbn, Boolean returned, LocalDate startDate, LocalDate endDate);

    Lending save(Lending lending);

    void delete(Lending lending);


    // Novos métodos para recomendação

    /**
     * Obtém uma lista dos empréstimos agrupados por gênero, ordenados pelo número de empréstimos.
     * @param limit Número máximo de gêneros a retornar.
     * @return Lista de empréstimos agrupados por gênero.
     */
    List<Lending> findTopGenres(int limit);

    /**
     * Obtém os empréstimos feitos por leitores dentro de uma faixa etária.
     * @param minAge Idade mínima do leitor.
     * @param maxAge Idade máxima do leitor.
     * @param limit Número máximo de empréstimos a retornar.
     * @return Lista de empréstimos na faixa etária especificada.
     */
    List<Lending> findByReaderAgeGroup(int minAge, int maxAge, int limit);
    List<Lending> findAll();
    List<Book> findBooksByGenres(List<String> genres, int limit);
    List<Book> findBooksByGenre(String genre, int limit);
    String findMostRequestedGenreByReader(Long readerId);

}
