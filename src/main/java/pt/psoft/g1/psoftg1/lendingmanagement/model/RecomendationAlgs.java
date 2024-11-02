package pt.psoft.g1.psoftg1.lendingmanagement.model;

import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
//import pt.psoft.g1.psoftg1.lendingmanagement.repository.LendingRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecomendationAlgs {

    // Algoritmo 1: Recomendação com base nos gêneros mais requisitados
    public static List<Book> recommendMostRequestedGenres(LendingRepository lendingRepository, int numberOfBooks, int numberOfGenres) {
        Map<String, Long> genreCounts = lendingRepository.findAll().stream()
                .map(lending -> lending.getBook().getGenre())
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));

        List<String> topGenres = genreCounts.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(numberOfGenres)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return lendingRepository.findBooksByGenres(topGenres, numberOfBooks);
    }

    // Algoritmo 2: Recomendação com base na idade do leitor
    public static List<Book> recommendByAgeGroup(LendingRepository lendingRepository, ReaderDetails readerDetails, int numberOfBooks) {
        int age = readerDetails.getAge();
        String genre;

        if (age < 10) {
            genre = "Infantil";
        } else if (age < 18) {
            genre = "Juvenil";
        } else {
            genre = lendingRepository.findMostRequestedGenreByReader(readerDetails.getId());
        }

        return lendingRepository.findBooksByGenre(genre, numberOfBooks);
    }

    // Algoritmo 3: Recomendação para leitores acima de 18 anos com gêneros mais requisitados
    public static List<Book> recommendByAgeGroupWithTopGenres(LendingRepository lendingRepository, ReaderDetails readerDetails, int numberOfBooks, int numberOfGenres) {
        int age = readerDetails.getAge();

        if (age < 18) {
            return recommendByAgeGroup(lendingRepository, readerDetails, numberOfBooks);
        } else {
            return recommendMostRequestedGenres(lendingRepository, numberOfBooks, numberOfGenres);
        }
    }
}
