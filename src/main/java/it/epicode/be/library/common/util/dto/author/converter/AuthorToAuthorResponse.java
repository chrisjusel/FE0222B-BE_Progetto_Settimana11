package it.epicode.be.library.common.util.dto.author.converter;

import it.epicode.be.library.common.util.dto.author.AuthorResponse;
import it.epicode.be.library.common.util.dto.book.BookResponse;
import it.epicode.be.library.common.util.dto.book.converter.BookToBookResponse;
import it.epicode.be.library.model.Author;
import it.epicode.be.library.model.Book;

public abstract class AuthorToAuthorResponse {

	public static AuthorResponse convert(Author author) {
		AuthorResponse response = new AuthorResponse();
		
		response.setId(author.getId());
		response.setName(author.getName());
		response.setSurname(author.getSurname());
		
		for(Book book : author.getBooks()) {
			BookResponse bookResponse = BookToBookResponse.convert(book);
			response.getBooks().add(bookResponse);
		}
		
		return response;
	}
}
