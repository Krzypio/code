package krzypio.forge;

import krzypio.forge.backend.authentication.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ForgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgeApplication.class, args);
	}

}
