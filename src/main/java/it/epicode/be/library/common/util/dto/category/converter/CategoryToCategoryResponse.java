package it.epicode.be.library.common.util.dto.category.converter;

import it.epicode.be.library.common.util.dto.book.BookResponse;
import it.epicode.be.library.common.util.dto.book.converter.BookToBookResponse;
import it.epicode.be.library.common.util.dto.category.CategoryResponse;
import it.epicode.be.library.model.Book;
import it.epicode.be.library.model.Category;

public abstract class CategoryToCategoryResponse {

	public static CategoryResponse convert(Category category) {
		CategoryResponse response = new CategoryResponse();
		response.setId(category.getId());
		response.setName(category.getName());
		
		for(Book book : category.getBooks()) {
			BookResponse bookResponse = BookToBookResponse.convert(book);
			response.getBooks().add(bookResponse);
		}
		
		return response;
	}
}
