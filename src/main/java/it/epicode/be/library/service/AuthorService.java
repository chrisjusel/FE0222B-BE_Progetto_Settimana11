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

import it.epicode.be.library.model.Author;
import it.epicode.be.library.repository.AuthorRepository;
import it.epicode.be.library.util.exception.AuthorNotFoundException;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	/*
	 * salvataggio di un nuovo autore
	 */
	public Author save(Author author) {
		return authorRepository.save(author);
	}

	/**
	 * modifica di un autore, se viene correttamente modificato
	 * restituisce i dati dell'autore, altrimenti lancia un'eccezione
	 * @param author
	 * @return
	 */
	public Author update(Author author) {
		Optional<Author> authorResult = authorRepository.findById(author.getId());

		if (authorResult.isPresent()) {
			Author authorUpdate = authorResult.get();
			authorUpdate.setName(author.getName());
			authorUpdate.setSurname(author.getSurname());
			authorUpdate.setBooks(author.getBooks());
			authorRepository.save(authorUpdate);
			return authorUpdate;
		} else
			throw new AuthorNotFoundException("Author not found");
	}

	/**
	 * recupero di un autore attraverso l'id, se viene trovato restituisce
	 * i suoi dati, altrimenti lancia un'eccezione
	 * @param author
	 * @return
	 */
	public Author findAuthorById(Long id) {
		Optional<Author> authorResult = authorRepository.findById(id);

		if (authorResult.isPresent()) {
			return authorResult.get();
		} else
			throw new AuthorNotFoundException("Author not found");
	}

	/**
	 * rimozione di un autore, se viene trovato viene rimosso, altrimenti
	 * lancia un'eccezione
	 * @param id
	 */
	public void delete(Long id) {
		if(authorRepository.findById(id).isPresent())
			authorRepository.deleteById(id);
		else throw new AuthorNotFoundException("Author not found");
	}
	
	/**
	 * Recupero di tutti gli autori
	 * @param pageable
	 * @return
	 */
	public Page<Author> findAll(Pageable pageable) {
		return authorRepository.findAll(pageable);
	}
}
