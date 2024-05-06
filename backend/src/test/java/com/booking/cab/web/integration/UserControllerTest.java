package com.booking.cab.web.integration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.booking.cab.domain.datastructure.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.ANY)
class UserControllerTest {

	MockMvc mockMvc;
	
	@Autowired
	public UserControllerTest(MockMvc mockMvc) {
		super();
		this.mockMvc = mockMvc;
	}
	
	@Test
	void getUser_nonInteger_returnsBadRequest() throws Exception {
		this.mockMvc.perform(get("/user/unknown")).andExpect(status().isBadRequest());
	}
	
	@Test
	void getUser_invalidUser_returnsNotFound() throws Exception {
		this.mockMvc.perform(get("/user/0")).andExpect(status().isNotFound());
	}
	
	@Test
	void getUser_valid() throws Exception {
		this.mockMvc.perform(get("/user/1")).andExpect(status().isOk())
			.andExpect(content().string(containsString("Alice")));
	}
	
	@Test
	void createUser_blankName_returnsError() throws Exception {
		User user = new User(123, "");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);
		this.mockMvc.perform(post("/user").content(jsonString).contentType("application/json"))
		.andExpect(status().isBadRequest());
	}

	@Test
	void createUser_valid() throws Exception {
		User user = new User(123, "TestUser");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);
		this.mockMvc.perform(post("/user").content(jsonString).contentType("application/json"))
			.andExpect(status().isCreated());		
		this.mockMvc.perform(delete("/user/123").content(jsonString).contentType("application/json"))
			.andExpect(status().isOk());
		
	}

}
