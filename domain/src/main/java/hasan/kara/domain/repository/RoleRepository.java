package hasan.kara.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hasan.kara.domain.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}