/**
 * Servizio che utilizza i repository per effettuare
 * le operazioni crud su e dal db
 */
package it.epicode.be.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.library.model.Book;
import it.epicode.be.library.repository.BookRepository;
import it.epicode.be.library.util.exception.BookException;
import it.epicode.be.library.util.exception.BookNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	/*
	 * salvataggio di un nuovo libro
	 */
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	/**
	 * modifica di un libro, se viene correttamente modificato
	 * restituisce i dati dell'autore, altrimenti lancia un'eccezione
	 * @param author
	 * @return
	 */
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
	
	/**
	 * recupero di un libro attraverso l'id, se viene trovato restituisce
	 * i suoi dati, altrimenti lancia un'eccezione
	 * @param author
	 * @return
	 */
	public Book findBookById(Long id) {
		Optional<Book> bookResult = bookRepository.findById(id);
		
		if(bookResult.isPresent()) {
			return bookResult.get();
		} else
			throw new BookNotFoundException("Book not found");
	}
	
	/**
	 * rimozione di un libro, se viene trovato viene rimosso, altrimenti
	 * lancia un'eccezione
	 * @param id
	 */
	public void delete(Long id) {
		if(bookRepository.findById(id).isPresent())
			bookRepository.deleteById(id);
		else throw new BookNotFoundException("Book not found");
	}
	
	/**
	 * recupero dei libri di un autore
	 * @param author
	 * @param pageable
	 * @return
	 */
	public Page<Book> findByAuthors(Long author, Pageable pageable) {
		return bookRepository.findByAuthorsId(author, pageable);
	}
	
	/**
	 * recupero dei libri appartenenti ad una categoria
	 * @param category
	 * @param pageable
	 * @return
	 */
	public Page<Book> findByCategories(String category, Pageable pageable) {
		return bookRepository.findByCategoriesName(category, pageable);
	}
	
	/**
	 * recupero di tutti i libri
	 * @param pageable
	 * @return
	 */
	public Page<Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}
}
