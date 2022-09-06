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
import it.epicode.be.library.common.util.dto.category.CategoryResponse;
import it.epicode.be.library.common.util.dto.category.converter.CategoryToCategoryResponse;
import it.epicode.be.library.model.Category;
import it.epicode.be.library.service.CategoryService;
import it.epicode.be.library.util.exception.BookNotFoundException;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/*
	 * SOLO gli admin possono accedervi;
	 * @SecurityRequirement indica su swagger-ui che questo
	 * metodo necessita di un barer token per poter essere
	 * utilizzato
	 */
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Category> save(@RequestBody Category category) {
		Category res = categoryService.save(category);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * I metodi senza l'annotazione @PreAuthorize sono accessibili 
	 * da tutti gli utenti loggati
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id) {
		Category res = categoryService.findCategoryById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<Page<CategoryResponse>> findAll(Pageable pageable) {
		Page<Category> findAll = categoryService.findAll(pageable);
		ArrayList<CategoryResponse> categoryResponse = new ArrayList<>();
		
		if(findAll.hasContent()) {
			for(Category category : findAll.getContent()) {
				CategoryResponse res = CategoryToCategoryResponse.convert(category);
				categoryResponse.add(res);
			}
			Page<CategoryResponse> response = new PageImpl<>(categoryResponse);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new BookNotFoundException("No book is present");
		}
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Category> update(@RequestBody Category category) {
		Category update = categoryService.update(category);
		return new ResponseEntity<>(update, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		categoryService.delete(id);
		return new ResponseEntity<String>("Category succeffully removed", HttpStatus.OK);
	}
}
