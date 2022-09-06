package it.epicode.be.library.common.util.dto.category;

import java.util.HashSet;
import java.util.Set;

import it.epicode.be.library.common.util.dto.book.BookResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {
	
	private Long id;
	private String name;
	private Set<BookResponse> books = new HashSet<>();
}
