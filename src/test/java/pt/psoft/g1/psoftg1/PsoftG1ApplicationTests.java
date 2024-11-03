package pt.psoft.g1.psoftg1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql.MySQLAuthorRepository;
import pt.psoft.g1.psoftg1.bookmanagement.infrastructure.repositories.mysql.MySQLBookRepository;
import pt.psoft.g1.psoftg1.configuration.db.MySQLConfig;
import pt.psoft.g1.psoftg1.genremanagement.infrastructure.repositories.mysql.MySQLGenreRepository;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.MySQLFineRepository;
import pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.mysql.MySQLLendingRepository;
import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql.MysqlReaderRepository;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.MysqlForbiddenNameRepository;
import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.mysql.MysqlPhotoRepository;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.MysqlUserRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EnableJpaRepositories(repositoryBaseClass = MySQLConfig.class)
class PsoftG1ApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
