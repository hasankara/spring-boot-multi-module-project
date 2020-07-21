package hasan.kara.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hasan.kara.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    public User findByUsername(String username);
    
}
