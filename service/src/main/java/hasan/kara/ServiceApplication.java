package hasan.kara;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hasan.kara.domain.service.DummyContentService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"hasan.kara"})
@EntityScan(basePackages = {"hasan.kara"})
@ComponentScan(basePackages = {"hasan.kara"})
public class ServiceApplication {

	Logger logger = LoggerFactory.getLogger(ServiceApplication.class);
	
	@Autowired
	private DummyContentService dummyContentService;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
	
	@PostConstruct
	private void init() {
		dummyContentService.createRoleAndUserIfNotExists();
	}
	
}
