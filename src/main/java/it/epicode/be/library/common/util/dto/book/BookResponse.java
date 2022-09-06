/**
 * Modello libro che verrà dato in output all'utente
 */
package it.epicode.be.library.common.util.dto.book;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResponse {

	private String title;
	private String publicationYear;
	private double price;
	private Set<AuthorResponse> authors = new HashSet<>();
	private Set<CategoryResponse> categories = new HashSet<>();
}
