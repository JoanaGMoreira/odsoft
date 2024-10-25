package pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mongodb.impl;

import org.springframework.data.mongodb.repository.MongoRepository;
import pt.psoft.g1.psoftg1.bookmanagement.model.Book;

public interface MongoBookRepository extends MongoRepository<Book, String> {
}
