package hasan.kara.domain.service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hasan.kara.domain.entity.Role;
import hasan.kara.domain.entity.User;
import hasan.kara.domain.repository.RoleRepository;
import hasan.kara.domain.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder crypt;
    
//    public User findUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    public Optional<User> findUserByID(Long id) {
        return userRepository.findById(id);
    }
    
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User saveUser(User user, String role) {
		UUID uuid = UUID.randomUUID();
		System.err.println(uuid.toString());
    	user.setUsername(user.getUsername());
        user.setPassword(crypt.encode(user.getPassword()));
        user.setActive(false);
        user.setEnabled(false);
        user.setDeleted(false);
        user.setEmailActivationUUID(uuid.toString());
        Role userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    @Bean
    public PasswordEncoder bcryptPasswordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
    
    public String getEncodedPassword(String plainTextPassword) {
    	return crypt.encode(plainTextPassword);
    }
    public Long countOfUsers() {
    	return userRepository.count();
    }
    
    public void deleteAllUsers() {
    	userRepository.deleteAll();
    }
    
    public List<User> getAll() {
    	return userRepository.findAll();
    }

    public List<User> findAllUsersByID(List<Long> ids) {
    	return userRepository.findAllById(ids);
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

}