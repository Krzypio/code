package krzypio.forge;

import krzypio.forge.backend.authentication.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ForgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgeApplication.class, args);

		Map<String, Integer> map = new HashMap<>(){{
			put("Adam", 1);
			put("anastazja", 2);
			put("Iza", 3);
		}};

		Map<String, Integer> result = map.entrySet()
				.stream()
				.filter(m -> m.getKey().startsWith("A") || m.getKey().startsWith("a"))
				.collect(Collectors.toMap(a -> a.getKey(), a -> a.getValue()));

		List<Integer> keyList = new ArrayList<Integer>(result.values());

		System.out.println("Result: " + keyList);
	}

}
