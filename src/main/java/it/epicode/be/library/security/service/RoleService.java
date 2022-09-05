/**
 * Classe di servizio per il salvataggio ed il recupero di un ruolo
 */
package it.epicode.be.library.security.service;

import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.library.model.Role;
import it.epicode.be.library.model.Roles;
import it.epicode.be.library.security.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	public Role findByRoleName(Roles roleName) {
		Optional<Role> role = roleRepository.findByRoleName(roleName);
		if(role.isPresent())
			return role.get();
		else
			return null;
	}
}
