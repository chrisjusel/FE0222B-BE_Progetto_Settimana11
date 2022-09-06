package it.epicode.be.library.common.util.dto.book.converter;

import it.epicode.be.library.common.util.dto.book.AuthorResponse;
import it.epicode.be.library.common.util.dto.book.BookResponse;
import it.epicode.be.library.common.util.dto.book.CategoryResponse;
import it.epicode.be.library.model.Author;
import it.epicode.be.library.model.Book;
import it.epicode.be.library.model.Category;

public abstract class BookToBookResponse {

	public static BookResponse convert(Book book) {
		BookResponse response = new BookResponse();
		response.setTitle(book.getTitle());
		response.setPublicationYear(book.getPublicationYear());
		response.setPrice(book.getPrice());
		
		for(Author author : book.getAuthors()) {
			AuthorResponse authorResponse = new AuthorResponse();
			authorResponse.setId(author.getId());
			authorResponse.setName(author.getName());
			authorResponse.setSurname(author.getSurname());
			response.getAuthors().add(authorResponse);
		}
		
		for(Category category : book.getCategories()) {
			CategoryResponse categoryResponse = new CategoryResponse();
			categoryResponse.setId(category.getId());
			categoryResponse.setName(category.getName());
			response.getCategories().add(categoryResponse);
		}
		
		return response;
	}
}
