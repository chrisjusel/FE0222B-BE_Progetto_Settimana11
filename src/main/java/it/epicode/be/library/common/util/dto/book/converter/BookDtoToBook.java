/**
 * Questa classe ha lo scopo di convertire il formato json che
 * arriva dall'esterno (che prevede gli id di autore e categorie)
 * in un'oggetto di tipo book che prevede al suo interno ulteriori
 * oggetti di tipo author e category
 */
package it.epicode.be.library.common.util.dto.book.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.epicode.be.library.common.util.dto.book.AuthorDto;
import it.epicode.be.library.common.util.dto.book.BookDto;
import it.epicode.be.library.common.util.dto.book.CategoryDto;
import it.epicode.be.library.model.Author;
import it.epicode.be.library.model.Book;
import it.epicode.be.library.model.Category;
import it.epicode.be.library.repository.AuthorRepository;
import it.epicode.be.library.repository.CategoryRepository;
import it.epicode.be.library.util.exception.AuthorNotFoundException;
import it.epicode.be.library.util.exception.CategoryNotFoundException;

@Component
public class BookDtoToBook {
	
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private CategoryRepository categoryService;
	
	public Book convert(BookDto bookDto) {
		Book book = new Book();
		book.setTitle(bookDto.getTitle());
		book.setPublicationYear(bookDto.getPublicationYear());
		book.setPrice(bookDto.getPrice());

		Set<Author> authors = new HashSet<>();
		
		for(AuthorDto authorDto : bookDto.getAuthors()) {
			Author author = new Author();
			var authorOpt = authorRepository.findById(authorDto.getId());
			if(authorOpt.isPresent())
				author = authorOpt.get();
			else throw new AuthorNotFoundException("Author not found");
			
			if(author != null)
				book.getAuthors().add(author);
			
		}
		
		for(CategoryDto categoryDto : bookDto.getCategories()) {
			Category category = new Category();
			var categoryOpt = categoryService.findById(categoryDto.getId());
			if(categoryOpt.isPresent())
				category = categoryOpt.get();
			else throw new CategoryNotFoundException("Category not found");
			
			if(category != null)
				book.getCategories().add(category);
			
		}
		
		return book;
		
	}
}
