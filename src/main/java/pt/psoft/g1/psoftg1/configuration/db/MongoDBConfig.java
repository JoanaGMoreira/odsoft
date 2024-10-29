package pt.psoft.g1.psoftg1.configuration.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mongodb.MongoDBAuthorRepository;
import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mongodb.MongoDBBookRepository;
import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mongodb.MongoDBGenreRepository;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mongodb.MongoDBFineRepository;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mongodb.MongoDBLendingRepository;
import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mongodb.MongoDBReaderDetailsRepository;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mongodb.MongoDBForbiddenNameRepository;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mongodb.MongoDBPhotoRepository;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mongodb.MongoDBUserRepository;


@Configuration
@Profile("mongodb")
@EnableMongoRepositories(basePackageClasses = {MongoDBAuthorRepository.class, MongoDBBookRepository.class, MongoDBGenreRepository.class,
                MongoDBFineRepository.class, MongoDBLendingRepository.class, MongoDBReaderDetailsRepository.class,
                MongoDBForbiddenNameRepository.class, MongoDBPhotoRepository.class, MongoDBUserRepository.class})
public class MongoDBConfig extends AbstractMongoClientConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public MongoClient mongoClient() {
        // Configure the MongoClient instance to connect to MongoDB
        return MongoClients.create(env.getProperty("spring.data.mongodb.uri"));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        // Configure the MongoTemplate to use the specified MongoDB connection
        return new MongoTemplate(mongoClient(), env.getProperty("spring.data.mongodb.database"));
    }

    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }

}
