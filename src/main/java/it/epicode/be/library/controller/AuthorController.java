/**
 * Controller degli endpoint rest relativi all'autore
 * 
 * E' stato usato il metodo di preautorizzazione
 * "hasAuthority" invece del hasRole in quanto quest'ultimo richiede
 * che i ruoli abbiano formato ROLE_NOMERUOLO ed i miei ruoli hanno
 * solo NOMERUOLO quindi "ADMIN", "USER", ecc.
 */
package it.epicode.be.library.controller;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.library.common.util.dto.author.AuthorResponse;
import it.epicode.be.library.common.util.dto.author.converter.AuthorToAuthorResponse;
import it.epicode.be.library.common.util.dto.book.BookResponse;
import it.epicode.be.library.common.util.dto.book.converter.BookToBookResponse;
import it.epicode.be.library.model.Author;
import it.epicode.be.library.model.Book;
import it.epicode.be.library.service.AuthorService;
import it.epicode.be.library.util.exception.BookNotFoundException;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	/*
	 * SOLO gli admin possono accedervi;
	 * 
	 * @SecurityRequirement indica su swagger-ui che questo metodo necessita di un
	 * barer token per poter essere utilizzato
	 */
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Author> save(@RequestBody Author author) {
		Author res = authorService.save(author);
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
	public ResponseEntity<Author> getById(@PathVariable Long id) {
		Author res = authorService.findAuthorById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/findall")
	public ResponseEntity<Page<AuthorResponse>> findAll(Pageable pageable) {
		Page<Author> findAll = authorService.findAll(pageable);
		ArrayList<AuthorResponse> authorResponse = new ArrayList<>();

		if (findAll.hasContent()) {
			for (Author author : findAll.getContent()) {
				AuthorResponse res = AuthorToAuthorResponse.convert(author);
				authorResponse.add(res);
			}
			Page<AuthorResponse> response = new PageImpl<>(authorResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new BookNotFoundException("No author is present");
		}
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Author> update(@RequestBody Author author) {
		Author update = authorService.update(author);
		return new ResponseEntity<>(update, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		authorService.delete(id);
		return new ResponseEntity<String>("Author succeffully removed", HttpStatus.OK);
	}
}
