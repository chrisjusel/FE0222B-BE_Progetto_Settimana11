package it.epicode.be.library.common.util.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorResponse {

	private Long id;
	private String name;
	private String Surname;
}
