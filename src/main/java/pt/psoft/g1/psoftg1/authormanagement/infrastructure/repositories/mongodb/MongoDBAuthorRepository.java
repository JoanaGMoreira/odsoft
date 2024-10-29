package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.repositories.AuthorRepository;

public interface MongoDBAuthorRepository extends MongoRepository<Author, String>, AuthorRepository {

}
