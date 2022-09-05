/**
 * Dto che fornisce il modello per l'inserimento di un nuovo libro
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
public class BookDto {

	private String title;
	private String publicationYear;
	private double price;
	private Set<CategoryDto> categories = new HashSet<>();
	private Set<AuthorDto> authors = new HashSet<>();
}
