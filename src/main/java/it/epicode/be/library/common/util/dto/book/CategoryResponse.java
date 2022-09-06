/**
 * Modello della categoria che verrà usato all'interno del modello
 * libro che verrà restituito in output all'utente
 */
package it.epicode.be.library.common.util.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {

	private Long id;
	private String name;
}
