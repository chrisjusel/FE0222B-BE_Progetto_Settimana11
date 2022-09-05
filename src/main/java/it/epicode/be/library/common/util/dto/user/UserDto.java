/**
 * Dto che fornisce il modello per la registrazione di un nuovo
 * utente
 */
package it.epicode.be.library.common.util.dto.user;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private String username;
	private String password;
	private String email;
	private List<String> roles = new ArrayList<>();
}
