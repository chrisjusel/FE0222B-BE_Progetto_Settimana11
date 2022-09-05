package it.epicode.be.library;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.epicode.be.library.model.Author;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username="user", password="password", authorities = {"USER"})
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
class AuthorTests {

	@LocalServerPort
	private int port;

	@Autowired MockMvc mockMvc;
	
	/**
	 * Questo metodo testa se lo stato della richiesta a questo endpoint restituisce 200
	 * @throws Exception
	 */
	@Test
	void testFindAuthorById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/authors/1")).andExpect(status().isOk());
	}
	
	/**
	 * Questo metodo testa se lo stato della richiesta a questo endpoint restituisce 200
	 * @throws Exception
	 */
	@Test
	void testFindAllAuthors() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/authors/findall"))
		.andExpect(status().isOk());
	}
	
	/**
	 * Questo metodo testa se l'inserimento di nuovo autore è andato a buon fine
	 * @throws Exception
	 */
	 @Test
	    @WithMockUser(username = "user", password = "password", authorities = {"ADMIN"})
	    public void addNewAuthor() throws Exception{
	        
	        Author author = new Author();
	        author.setName("Davide");
	        author.setSurname("Rossi");
	        
	        ObjectMapper objectMapper = new ObjectMapper();
	        String json = objectMapper.writeValueAsString(author);
	    
	        MvcResult result = mockMvc.perform(
	                post("http://localhost:8080/api/authors/save")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect(status().isOk())
	                .andExpect(content().json("{'name':'Davide'}"))
	                .andExpect(content().json("{'surname':'Rossi'}"))
	                .andReturn();
	    }

	 /**
	  * Questo metodo testa se un autore è stato cancellato correttamente
	  * @throws Exception
	  */
	 @Test
		@WithMockUser(username="user", password="password", authorities = {"ADMIN"})
		void testDeleteAuthor() throws Exception {
			this.mockMvc.perform(delete("http://localhost:8080/api/authors/delete/1"))
			.andExpect(status().isOk()).andExpect(content().string(containsString("Author succeffully removed")));
		}
}
