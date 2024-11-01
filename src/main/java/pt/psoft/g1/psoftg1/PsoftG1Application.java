package pt.psoft.g1.psoftg1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pt.psoft.g1.psoftg1.usermanagement.repositories") // Specify your package here
public class PsoftG1Application {

	public static void main(String[] args) {
		SpringApplication.run(PsoftG1Application.class, args);
	}

}
