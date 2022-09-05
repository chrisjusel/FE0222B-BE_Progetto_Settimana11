/**
 * Modello per la risposta da parte del server quando un utente si logga correttamente
 */
package it.epicode.be.library.security.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

	private final String type = "Bearer";
	private String token;
	private List<String> roles;
}
