package auth.web.integration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import auth.gateway.UserGatewayInterface;
import auth.repository.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.ANY)
@WithMockUser(username = "Alice", password = "dummy")
class UserControllerTest {
	MockMvc mockMvc;
	private UserGatewayInterface userGateway;
	
	@Autowired
	public UserControllerTest(
			MockMvc mockMvc,
			UserGatewayInterface userGateway) {
		super();
		this.mockMvc = mockMvc;
		this.userGateway = userGateway;
	}
	
	@Test
	void createUser_blankName_returnsError() throws Exception {
		User user = new User(123, "", "", "", true);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);
		this.mockMvc.perform(
			post("/user/register")
			.content(jsonString)
			.contentType("application/json")
			.with(csrf())
		)
		.andExpect(status().isBadRequest());
	}

	@Test
	void createUser_valid() throws Exception {
		User user = new User(123, "TestUser", "testpass", "USER", true);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);
		this.mockMvc.perform(
			post("/user/register")
			.content(jsonString)
			.contentType("application/json")
			.with(csrf())
		)
		.andExpect(status().isCreated());
		User storedUser = userGateway.findById(123);
		Assert.isInstanceOf(User.class, storedUser);
		Assert.isTrue(storedUser.getUsername().equals("TestUser"), storedUser.getUsername() + " is not \"TestUser\"");
		userGateway.deleteById(123);
	}
}
