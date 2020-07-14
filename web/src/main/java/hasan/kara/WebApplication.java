package hasan.kara;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hasan.kara.domain.entity.Role;
import hasan.kara.domain.entity.User;
import hasan.kara.domain.repository.RoleRepository;
import hasan.kara.domain.repository.UserRepository;
import hasan.kara.domain.service.UserService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"hasan.kara"})
@EntityScan(basePackages = {"hasan.kara"})
@ComponentScan(basePackages = {"hasan.kara"})
public class WebApplication {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
	@PostConstruct
	private void init() {
		//userRepository.deleteAll();
		if(roleRepository.count() == 0) {
			Role roleAdmin = new Role();
			roleAdmin.setRole("ROLE_ADMIN");
			Role roleUser = new Role();
			roleUser.setRole("ROLE_USER");
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			roleRepository.saveAll(roles);
		}
		if(userRepository.count() == 0) {
			System.err.println("no users existed default user is created");
			Role userRole = roleRepository.findByRole("ROLE_USER");
			Role adminRole = roleRepository.findByRole("ROLE_ADMIN");
			Set<Role> roles = new HashSet<Role>();
			roles.add(userRole);
			roles.add(adminRole);

			User u = new User();

			u.setName("Hasan");
			u.setLastName("Kara");
			u.setEmail("h.kara27@gmail.com");
			u.setUsername("hasankara");
			u.setPassword(userService.getEncodedPassword("123"));
			u.setEnabled(true);
			u.setActive(true);
			u.setDeleted(false);
			u.setRoles(roles);
			userRepository.save(u);
			//mailClient.sendEmailVerification(newUser);
		}
	}

}
