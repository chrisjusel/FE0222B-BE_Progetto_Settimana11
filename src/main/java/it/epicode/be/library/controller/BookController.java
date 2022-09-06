/**
 * Controller degli endpoint rest relativi ai libri
 * 
 * E' stato usato il metodo di preautorizzazione
 * "hasAuthority" invece del hasRole in quanto quest'ultimo richiede
 * che i ruoli abbiano formato ROLE_NOMERUOLO ed i miei ruoli hanno
 * solo NOMERUOLO quindi "ADMIN", "USER", ecc.
 */
package it.epicode.be.library.controller;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.library.common.util.dto.book.BookDto;
import it.epicode.be.library.common.util.dto.book.BookResponse;
import it.epicode.be.library.common.util.dto.book.converter.BookDtoToBook;
import it.epicode.be.library.common.util.dto.book.converter.BookToBookResponse;
import it.epicode.be.library.model.Book;
import it.epicode.be.library.service.BookService;
import it.epicode.be.library.util.exception.BookNotFoundException;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookDtoToBook bookConverter;

	/*
	 * SOLO gli admin possono accedervi;
	 * 
	 * @SecurityRequirement indica su swagger-ui che questo metodo necessita di un
	 * barer token per poter essere utilizzato
	 */
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<BookResponse> save(@RequestBody BookDto bookDto) {
		Book book = bookConverter.convert(bookDto);
		Book save = bookService.save(book);
		BookResponse res = BookToBookResponse.convert(book);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * I metodi senza l'annotazione @PreAuthorize sono accessibili da tutti gli
	 * utenti loggati
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BookResponse> getById(@PathVariable Long id) {
		Book bookFoundById = bookService.findBookById(id);
		BookResponse res = BookToBookResponse.convert(bookFoundById);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/findbyauthor")
	public ResponseEntity<Page<BookResponse>> findByAuthors(@RequestParam Long author, Pageable pageable) {
		Page<Book> booksFoundByAuthors = bookService.findByAuthors(author, pageable);
		ArrayList<BookResponse> booksResponse = new ArrayList<>();

		if (booksFoundByAuthors.hasContent()) {
			for (Book book : booksFoundByAuthors.getContent()) {
				BookResponse res = BookToBookResponse.convert(book);
				booksResponse.add(res);
			}
			Page<BookResponse> response = new PageImpl<>(booksResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new BookNotFoundException("No books found for this author");
		}
	}

	@GetMapping("/findbycategory")
	public ResponseEntity<Page<BookResponse>> findByCategories(@RequestParam String category, Pageable pageable) {
		Page<Book> booksFoundByCategory = bookService.findByCategories(category, pageable);
		ArrayList<BookResponse> booksResponse = new ArrayList<>();

		if (booksFoundByCategory.hasContent()) {
			for (Book book : booksFoundByCategory.getContent()) {
				BookResponse res = BookToBookResponse.convert(book);
				booksResponse.add(res);
			}
			Page<BookResponse> response = new PageImpl<>(booksResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new BookNotFoundException("No books found for this category");
		}
	}

	@GetMapping("/findall")
	public ResponseEntity<Page<BookResponse>> findAll(Pageable pageable) {
		Page<Book> allBooksFound = bookService.findAll(pageable);
		ArrayList<BookResponse> booksResponse = new ArrayList<>();

		if (allBooksFound.hasContent()) {
			for (Book book : allBooksFound.getContent()) {
				BookResponse res = BookToBookResponse.convert(book);
				booksResponse.add(res);
			}
			Page<BookResponse> response = new PageImpl<>(booksResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new BookNotFoundException("No book is present");
		}
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<BookResponse> update(@RequestBody Book book) {
		Book updatedBook = bookService.update(book);
		BookResponse res = BookToBookResponse.convert(updatedBook);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		bookService.delete(id);
		return new ResponseEntity<String>("Book succeffully removed", HttpStatus.OK);
	}

}
