package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mongodb;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Fine;
import pt.psoft.g1.psoftg1.lendingmanagement.repositories.FineRepository;

@Repository
@Profile("mongodb")
public interface MongoBdFineRepository extends MongoRepository<Fine, String>, FineRepository {

}
