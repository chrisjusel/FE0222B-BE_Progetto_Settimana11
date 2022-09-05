package it.epicode.be.library;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username="user", password="password", authorities = {"USER"})
class BookTests {

	@LocalServerPort
	private int port;

	@Autowired MockMvc mockMvc;
	
	@Test
	void testGetBookById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/books/1")).andExpect(status().isOk());
	}
	
	@Test
	void testFindByCategory() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/books/findbycategory?category=Avventura"))
		.andExpect(status().isOk());
	}
	
	@Test
	void testFindByAuthor() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/books/findbyauthor?author=1"))
		.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="user", password="password", authorities = {"ADMIN"})
	void testDeleteBook() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/api/books/delete/1"))
		.andExpect(status().isOk()).andExpect(content().string(containsString("Book succeffully removed")));
	}
}
