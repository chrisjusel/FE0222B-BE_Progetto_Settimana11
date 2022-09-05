/**
 * Inserimento dati di test
 */
package it.epicode.be.library.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.be.library.model.Author;
import it.epicode.be.library.model.Book;
import it.epicode.be.library.model.Category;
import it.epicode.be.library.model.Role;
import it.epicode.be.library.model.Roles;
import it.epicode.be.library.model.User;
import it.epicode.be.library.security.repository.RoleRepository;
import it.epicode.be.library.security.repository.UserRepository;
import it.epicode.be.library.security.service.RoleService;
import it.epicode.be.library.service.BookService;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category();
		category.setName("Romanzo");

		Category category1 = new Category();
		category1.setName("Avventura");

		Author author = new Author();
		author.setName("Ciccio");
		author.setSurname("Mario");

		Book book = new Book();
		book.setPrice(12.99);
		book.setTitle("I promessi sposi");
		book.setPublicationYear("1899");
		book.getAuthors().add(author);
		book.getCategories().add(category);
		book.getCategories().add(category1);
		category.getBooks().add(book);

		bookService.save(book);

		initializeRoles();

	}

	/**
	 * Ogni ruolo presente nell'enumerazione dei ruoli
	 * viene mappato nel db
	 */
	public void initializeRoles() {
		for (Roles role : Roles.values()) {
			Role saveToDb = new Role();
			saveToDb.setRoleName(role);
			roleService.save(saveToDb);
		}
	}
	
}
