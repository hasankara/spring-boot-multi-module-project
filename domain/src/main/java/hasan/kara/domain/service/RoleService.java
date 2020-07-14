package hasan.kara.domain.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hasan.kara.domain.entity.Role;
import hasan.kara.domain.repository.RoleRepository;

@Service
public class RoleService{

	@Autowired
    private RoleRepository roleRepository;
    
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	public Role findByName(String role) {
		return roleRepository.findByRole(role);
	}
}