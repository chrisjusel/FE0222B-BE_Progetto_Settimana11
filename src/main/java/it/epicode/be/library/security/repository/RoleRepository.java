package it.epicode.be.library.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.library.model.Role;
import it.epicode.be.library.model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	public Optional<Role> findByRoleName(Roles name);
}
