package it.epicode.be.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.library.model.Book;
import it.epicode.be.library.repository.BookRepository;
import it.epicode.be.library.util.exception.BookException;
import it.epicode.be.library.util.exception.BookNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public Book update(Book book) {
		Optional<Book> bookResult = bookRepository.findById(book.getId());
		
		if(bookResult.isPresent()) {
			Book bookUpdate = bookResult.get();
			bookUpdate.setPrice(book.getPrice());
			bookUpdate.setTitle(book.getTitle());
			bookRepository.save(bookUpdate);
			return bookUpdate;
		} else 
			throw new BookException("No changes has been executed for this book");
	}
	
	public Book findBookById(Long id) {
		Optional<Book> bookResult = bookRepository.findById(id);
		
		if(bookResult.isPresent()) {
			return bookResult.get();
		} else
			throw new BookNotFoundException("Book not found");
	}
	
	public void delete(Long id) {
		if(bookRepository.findById(id).isPresent())
			bookRepository.deleteById(id);
		else throw new BookNotFoundException("Book not found");
	}
}
