package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mongodb;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;
import pt.psoft.g1.psoftg1.bookmanagement.repositories.BookRepository;

@Repository
@Profile("mongodb")
public interface MongoDBBookRepository extends MongoRepository<Book, String>, BookRepository {
}
