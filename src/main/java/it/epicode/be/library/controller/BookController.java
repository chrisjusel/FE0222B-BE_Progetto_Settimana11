package it.epicode.be.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.library.model.Book;
import it.epicode.be.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/save")
	public ResponseEntity<Book> save(@RequestBody Book book) {
		Book res = bookService.save(book);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		Book res = bookService.findBookById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Book> update(@RequestBody Book book){
		Book update = bookService.update(book);
		return new ResponseEntity<Book>(update, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		bookService.delete(id);
		return new ResponseEntity<String>("Book succeffully removed", HttpStatus.OK);
	}
}
