package hasan.kara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"hasan.kara"})
@EntityScan(basePackages = {"hasan.kara"})
@ComponentScan(basePackages = {"hasan.kara"})
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
}
