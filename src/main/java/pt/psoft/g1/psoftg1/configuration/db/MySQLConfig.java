package pt.psoft.g1.psoftg1.configuration.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql.MySQLAuthorRepository;
import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql.MySQLBookRepository;
import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mysql.MySQLGenreRepository;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.MySQLFineRepository;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.MySQLLendingRepository;
import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.MysqlReaderRepositoryImpl;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.MysqlForbiddenNameRepository;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.MysqlPhotoRepository;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.MysqlUserRepository;

import javax.sql.DataSource;

@Configuration
@Profile("mysql")
@EnableJpaRepositories(basePackageClasses = {MySQLAuthorRepository.class, MySQLBookRepository.class, MySQLGenreRepository.class,
                MySQLFineRepository.class, MySQLLendingRepository.class, MysqlReaderRepositoryImpl.class,
                MysqlForbiddenNameRepository.class, MysqlPhotoRepository.class, MysqlUserRepository.class})
public class MySQLConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("spring.datasource.url"));
        config.setUsername(env.getProperty("spring.datasource.username"));
        config.setPassword(env.getProperty("spring.datasource.password"));
        config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return new HikariDataSource(config);
    }
}
