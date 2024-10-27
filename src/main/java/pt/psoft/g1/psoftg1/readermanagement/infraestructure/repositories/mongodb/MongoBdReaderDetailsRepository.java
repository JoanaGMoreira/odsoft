package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mongodb;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import pt.psoft.g1.psoftg1.readermanagement.repositories.ReaderRepository;

@Repository
@Profile("mongodb")
public interface MongoBdReaderDetailsRepository extends MongoRepository<ReaderDetails, String>, ReaderRepository {

}
