package com.booking.cab.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.booking.cab.domain.gateway.BookingGatewayInterface;
import com.booking.cab.domain.usecase.CreateBooking;
import com.booking.cab.repository.entity.Booking;
import com.booking.cab.repository.entity.BookingRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {
	private BookingGatewayInterface bookingGateway;
	
	private CreateBooking createBooking;

	public BookingController(
		BookingGatewayInterface bookingGateway, 
		CreateBooking createBooking
	) {
		this.bookingGateway = bookingGateway;
		this.createBooking = createBooking;
	}

	@GetMapping
	public List<Booking> get(@AuthenticationPrincipal Jwt token) {
		int userId = extractUserId(token);
		return bookingGateway.findByUserId(userId);
	}

	private int extractUserId(Jwt token) {
		Object claim = token.getClaim("userId");
		// JWT claims can be Integer, Long, or String depending on the JSON library and numeric value
		if (claim instanceof Number) {
			return ((Number)claim).intValue();
		}
		if (claim instanceof String) {
			try {
				return Integer.parseInt((String) claim);
			} catch (NumberFormatException ex) {
				throw new IllegalArgumentException("userId claim is not a valid integer: " + claim, ex);
			}
		}
		throw new IllegalArgumentException("userId claim is missing or not numeric: " + claim);
	}

	@PostMapping("/request")
	public ResponseEntity<Booking> requestBooking(
			@Valid @RequestBody BookingRequest request, 
			@AuthenticationPrincipal Jwt token) {
		int userId = extractUserId(token);
		request.setUserId(userId);
		Booking booking = createBooking.execute(request);
		URI nextUri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(booking.getId())
			.toUri();
		return ResponseEntity.created(nextUri).build();
	}

}
