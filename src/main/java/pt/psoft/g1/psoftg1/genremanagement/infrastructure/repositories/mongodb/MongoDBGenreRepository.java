package pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.genremanagement.repositories.GenreRepository;

public interface MongoDBGenreRepository extends MongoRepository<Genre, String>, GenreRepository {
}
