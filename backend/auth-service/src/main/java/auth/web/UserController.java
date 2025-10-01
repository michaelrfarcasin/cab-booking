package auth.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import auth.exception.UserNotFoundException;
import auth.gateway.UserGatewayInterface;
import auth.repository.entity.User;
import auth.security.datastructure.JwtTokenRequest;
import auth.security.service.BookingUserDetailsService;
import auth.security.service.JwtTokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserGatewayInterface userGateway;
	private BookingUserDetailsService userDetailsService;
	private JwtTokenService tokenService;
	private AuthenticationManager authenticationManager;
	
	public UserController(
		UserGatewayInterface userGateway, 
		BookingUserDetailsService userDetailsService,
		JwtTokenService tokenService, 
		AuthenticationManager authenticationManager
	) {
		this.userGateway = userGateway;
		this.userDetailsService = userDetailsService;
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable int id) {
		User user = userGateway.findById(id);
		if (user == null) {
			throw new UserNotFoundException("Id: " + id);
		}
		return user;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDetailsService.addUser(user);
		URI nextUri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(nextUri).build();
	}

	@PostMapping("/generateToken")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody JwtTokenRequest request) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.username(), request.password())
		);
		if (authentication.isAuthenticated()) {
			User user = userGateway.findByUsername(request.username());
			String token = tokenService.generateToken(request.username(), user.getId());
			return ResponseEntity.ok(token);
		}
		throw new UsernameNotFoundException("Invalid user request");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		userGateway.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
