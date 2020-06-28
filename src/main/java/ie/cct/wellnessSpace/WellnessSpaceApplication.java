package ie.cct.wellnessSpace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan (basePackages = {"ie.cct.wellnessSpace.Entities"})
@EnableJpaRepositories (basePackages = {"ie.cct.wellnessSpace.Repository"})
@ComponentScan (basePackages = {"ie.cct.wellnessSpace.Controllers"})
public class WellnessSpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellnessSpaceApplication.class, args);
	}

}
