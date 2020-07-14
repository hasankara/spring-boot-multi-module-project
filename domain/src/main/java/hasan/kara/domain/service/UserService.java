package hasan.kara.domain.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hasan.kara.domain.entity.User;
import hasan.kara.domain.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private PasswordEncoder crypt;
    
    public Optional<User> findUserByID(Long id) {
        return userRepository.findById(id);
    }
    
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User save(User user) {
    	return userRepository.save(user);
    }
    
    public List<User> getAll() {
    	return userRepository.findAll();
    }
    
	public Long count() {
		return userRepository.count();
	}
    
    public String getEncodedPassword(String plainTextPassword) {
    	return crypt.encode(plainTextPassword);
    }
    
    @Bean
    public PasswordEncoder bcryptPasswordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

}