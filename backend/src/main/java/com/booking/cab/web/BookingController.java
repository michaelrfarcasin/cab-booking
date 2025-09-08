package com.booking.cab.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.booking.cab.domain.gateway.BookingGatewayInterface;
import com.booking.cab.domain.gateway.UserGatewayInterface;
import com.booking.cab.domain.usecase.CreateBooking;
import com.booking.cab.exception.UserNotFoundException;
import com.booking.cab.repository.entity.Booking;
import com.booking.cab.repository.entity.BookingRequest;
import com.booking.cab.repository.entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {
	private UserGatewayInterface userGateway;
	
	private BookingGatewayInterface bookingGateway;
	
	private CreateBooking createBooking;

	@Autowired
	public BookingController(
			UserGatewayInterface userGateway, 
			BookingGatewayInterface bookingGateway, 
			CreateBooking createBooking) {
		this.userGateway = userGateway;
		this.bookingGateway = bookingGateway;
		this.createBooking = createBooking;
	}

	@GetMapping("/user/{name}")
	public List<Booking> get(@PathVariable String name) {
		int userId = getIdForUser(name);

		return bookingGateway.findByUserId(userId);
	}
	
	private int getIdForUser(String name) {
		User user = userGateway.findByUsername(name);
		if (user == null) {
			throw new UserNotFoundException("Name: " + name);
		}

		return user.getId();
	}

	@PostMapping("/request/{name}")
	public ResponseEntity<Booking> requestBooking(
			@PathVariable String name,
			@Valid @RequestBody BookingRequest request) {
		int userId = getIdForUser(name);
		request.setUserId(userId);
		Booking booking = createBooking.execute(request);
		URI nextUri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(booking.getId())
				.toUri();
		return ResponseEntity.created(nextUri).build();
	}
}
