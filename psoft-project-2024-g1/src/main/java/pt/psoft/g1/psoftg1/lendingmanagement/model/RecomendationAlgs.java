package pt.psoft.g1.psoftg1.lendingmanagement.model;

import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import pt.psoft.g1.psoftg1.lendingmanagement.repositories.LendingRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecomendationAlgs {

    // Algoritmo 1: Recomendação com base nos gêneros mais requisitados
    public static List<Book> recommendMostRequestedGenres(LendingRepository lendingRepository, int numberOfBooks, int numberOfGenres) {
        // Obtém todos os empréstimos e agrupa por gênero (usando getGenre() para obter o nome do gênero)
        Map<String, Long> genreCounts = lendingRepository.findAll().stream()
                .map(lending -> lending.getBook().getGenre().getGenre()) // Use getGenre() to get the genre name as String
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));

        // Seleciona os gêneros mais requisitados
        List<String> topGenres = genreCounts.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(numberOfGenres)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Busca os livros desses gêneros limitando ao número de livros especificado
        return lendingRepository.findBooksByGenres(topGenres, numberOfBooks);
    }

    // Algoritmo 2: Recomendação com base na idade do leitor
    public static List<Book> recommendByAgeGroup(LendingRepository lendingRepository, ReaderDetails readerDetails, int numberOfBooks) {
        int age = readerDetails.getAge(); // Certifique-se de que `getAge()` exista em `ReaderDetails`
        String genre;

        // Define o gênero recomendado com base na idade
        if (age < 10) {
            genre = "Infantil";
        } else if (age < 18) {
            genre = "Juvenil";
        } else {
            // Gênero mais requisitado pelo leitor
            genre = lendingRepository.findMostRequestedGenreByReader(readerDetails.getId());
        }

        // Busca os livros recomendados por gênero
        return lendingRepository.findBooksByGenre(genre, numberOfBooks);
    }

    // Algoritmo 3: Recomendação para leitores acima de 18 anos com gêneros mais requisitados
    public static List<Book> recommendByAgeGroupWithTopGenres(LendingRepository lendingRepository, ReaderDetails readerDetails, int numberOfBooks, int numberOfGenres) {
        int age = readerDetails.getAge();

        // Recomendações por faixa etária
        if (age < 18) {
            return recommendByAgeGroup(lendingRepository, readerDetails, numberOfBooks);
        } else {
            // Recomendações para adultos com os gêneros mais requisitados
            return recommendMostRequestedGenres(lendingRepository, numberOfBooks, numberOfGenres);
        }
    }
}
