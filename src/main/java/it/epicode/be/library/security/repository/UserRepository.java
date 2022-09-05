package it.epicode.be.library.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.library.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findById(Long id);

	public Optional<User> findByUsername(String username);

	public boolean existsByEmail(String email);

	public boolean existsByUsername(String username);

}
