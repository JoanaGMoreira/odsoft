package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mongodb;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.shared.model.ForbiddenName;
import pt.psoft.g1.psoftg1.shared.repositories.ForbiddenNameRepository;

@Repository
@Profile("mongodb")
public interface MongoDBForbiddenNameRepository extends MongoRepository<ForbiddenName, String>, ForbiddenNameRepository {

}