package it.epicode.be.library;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username="user", password="password", authorities = {"USER"})
class CategoryTests {

//	@LocalServerPort
//	private int port;
//
//	@Autowired MockMvc mockMvc;
//	
//	@Test
//	void testFindCategoryById() throws Exception {
//		this.mockMvc.perform(get("http://localhost:8080/api/categories/1")).andExpect(status().isOk());
//	}
//	
//	@Test
//	void testFindAllCategories() throws Exception {
//		this.mockMvc.perform(get("http://localhost:8080/api/categories/findall"))
//		.andExpect(status().isOk());
//	}
//	
//	@Test
//	@WithMockUser(username="user", password="password", authorities = {"ADMIN"})
//	void testDeleteCategory() throws Exception {
//		this.mockMvc.perform(delete("http://localhost:8080/api/categories/delete/1"))
//		.andExpect(status().isOk()).andExpect(content().string(containsString("Category succeffully removed")));
//	}

}
