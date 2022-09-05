/**
 * Questo controller espone i metodi di login e registrazione
 */
package it.epicode.be.library.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.library.common.util.dto.user.UserDto;
import it.epicode.be.library.model.User;
import it.epicode.be.library.security.jwt.JwtUtils;
import it.epicode.be.library.security.model.LoginRequest;
import it.epicode.be.library.security.model.LoginResponse;
import it.epicode.be.library.security.service.UserDetailsImpl;
import it.epicode.be.library.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * In questo metodo viene generato e restituito il token all'interno
	 * del model di risposta creato in precedenza
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		LoginResponse loginResponse = new LoginResponse();
		
		loginResponse.setToken(token);
		loginResponse.setRoles(roles);
		
		return ResponseEntity.ok(loginResponse);
	}
	
	/**
	 * Salvataggio di un nuovo utente sul db
	 * @param userDto
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<User> save(@RequestBody UserDto userDto) {
		User res = userService.save(userDto);
		return new ResponseEntity<User>(res, HttpStatus.OK);
	}
}
