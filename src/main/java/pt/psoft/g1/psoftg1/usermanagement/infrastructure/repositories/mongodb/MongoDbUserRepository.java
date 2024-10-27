package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mongodb;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.usermanagement.model.User;
import pt.psoft.g1.psoftg1.usermanagement.repositories.UserRepository;

@Repository
@Profile("mongodb")
public interface MongoDbUserRepository extends MongoRepository<User, String>, UserRepository {
}
