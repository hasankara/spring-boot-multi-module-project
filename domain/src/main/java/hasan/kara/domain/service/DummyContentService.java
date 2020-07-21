package hasan.kara.domain.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hasan.kara.domain.entity.Role;
import hasan.kara.domain.entity.User;

@Service
public class DummyContentService {

	Logger logger = LoggerFactory.getLogger(DummyContentService.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	public void createRoleAndUserIfNotExists() {
		if(roleService.count() == 0) {
			logger.info("No role exists adding roles ROLE_ADMIN and ROLE_USER");
			Role roleAdmin = new Role();
			roleAdmin.setRole("ROLE_ADMIN");
			Role roleUser = new Role();
			roleUser.setRole("ROLE_USER");
			List<Role> roles = new ArrayList<>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			roleService.saveAll(roles);
		}
		if(userService.count() == 0) {
			logger.info("No users exists adding default user with roles: ROLE_ADMIN, ROLE_USER");
			System.err.println("no users existed default user is created");
			Role userRole = roleService.findByRoleName("ROLE_USER");
			Role adminRole = roleService.findByRoleName("ROLE_ADMIN");
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
			userService.save(u);
		}
	}



}