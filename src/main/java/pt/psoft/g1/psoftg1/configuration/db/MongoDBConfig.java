package pt.psoft.g1.psoftg1.configuration.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@Profile("mongodb")
@EnableMongoRepositories(basePackages = {"pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mongodb",
        "pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mongodb", "pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mongodb",
        "pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mongodb", "pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mongodb",
        "pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mongodb", "pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mongodb"})
public class MongoDBConfig extends AbstractMongoClientConfiguration {
    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(env.getProperty("spring.data.mongodb.uri"));
    }
}
