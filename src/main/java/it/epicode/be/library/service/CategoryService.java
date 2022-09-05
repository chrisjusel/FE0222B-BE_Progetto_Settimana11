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

import it.epicode.be.library.model.Category;
import it.epicode.be.library.repository.CategoryRepository;
import it.epicode.be.library.util.exception.CategoryNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/*
	 * salvataggio di una nuova categoria
	 */
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	/**
	 * modifica di una categoria, se viene correttamente modificata
	 * restituisce i dati dell'autore, altrimenti lancia un'eccezione
	 * @param author
	 * @return
	 */
	public Category update(Category category) {
		Optional<Category> categoryResult = categoryRepository.findById(category.getId());

		if (categoryResult.isPresent()) {
			Category categoryUpdate = categoryResult.get();
			categoryUpdate.setName(category.getName());
			categoryUpdate.setBooks(category.getBooks());
			categoryRepository.save(categoryUpdate);
			return categoryUpdate;
		} else
			throw new CategoryNotFoundException("Category not found");
	}

	/**
	 * recupero di una categoria attraverso l'id, se viene trovata restituisce
	 * i suoi dati, altrimenti lancia un'eccezione
	 * @param author
	 * @return
	 */
	public Category findCategoryById(Long id) {
		Optional<Category> categoryResult = categoryRepository.findById(id);

		if (categoryResult.isPresent()) {
			return categoryResult.get();
		} else
			throw new CategoryNotFoundException("Category not found");
	}

	/**
	 * recupero di una categoria attraverso l'id, se viene trovata restituisce
	 * i suoi dati, altrimenti lancia un'eccezione
	 * @param author
	 * @return
	 */
	public void delete(Long id) {
		if (categoryRepository.findById(id).isPresent())
			categoryRepository.deleteById(id);
		else
			throw new CategoryNotFoundException("Category not found");
	}
	
	/**
	 * recupero di tutte le categorie
	 * @param pageable
	 * @return
	 */
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}
}
