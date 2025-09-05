package com.booking.cab.web.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.booking.cab.domain.datastructure.BookingRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.ANY)
@WithMockUser(username = "Alice")
class BookingControllerTest {

	MockMvc mockMvc;
	
	@Autowired
	public BookingControllerTest(MockMvc mockMvc) {
		super();
		this.mockMvc = mockMvc;
	}
	
	private static String toJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
		return mapper.writeValueAsString(object);
	}
	
	@Test
	void getBookingsForUser() throws Exception {
		this.mockMvc.perform(get("/booking/user/Alice")).andExpect(status().isOk())
			.andExpect(content().string(containsString("Alpha")))
			.andExpect(content().string(not(containsString("Charlie"))));
	}
	
	@Test
	void requestBooking() throws Exception {
		BookingRequest request = new BookingRequest();
		request.setPickUpLatitude(39.7294);
		request.setPickUpLongitude(-104.8319); 
		request.setDropOffLatitude(39.7392);
		request.setDropOffLongitude(-104.9903);
		String jsonString = toJson(request);
		this.mockMvc.perform(
				post("/booking/request/Alice")
				.content(jsonString)
				.contentType("application/json")
				.with(csrf())
			)
			.andExpect(status().isCreated());
	}

}
