package com.booking.cab.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.booking.cab.domain.datastructure.User;
import com.booking.cab.domain.gateway.UserGatewayInterface;
import com.booking.cab.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserGatewayInterface userGateway;
	
	@Autowired
	public UserController(UserGatewayInterface userGateway) {
		super();
		this.userGateway = userGateway;
	}

	@GetMapping
	public List<User> getUsers() {
		return userGateway.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable int id) {
		User user = userGateway.findById(id);
		if (user == null) {
			throw new UserNotFoundException("Id: " + id);
		}
		return user;
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userGateway.save(user);
		URI nextUri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(nextUri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		userGateway.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
