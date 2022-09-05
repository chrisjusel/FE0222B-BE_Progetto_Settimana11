package it.epicode.be.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.library.common.util.dto.user.UserDto;
import it.epicode.be.library.common.util.dto.user.converter.UserDtoToUser;
import it.epicode.be.library.model.User;
import it.epicode.be.library.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDtoToUser converter;
	
	/**
	 * In questo metodo vengono convertiti i dati inseriti dal json
	 * in un utente per poi essere salvato nel db
	 * @param userDto
	 * @return
	 */
	public User save(UserDto userDto) {
		User res = converter.convert(userDto);
		return userRepository.save(res);
	}
}
