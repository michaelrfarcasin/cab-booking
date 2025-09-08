package com.booking.cab.domain.usecase;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.cab.domain.businessobject.Location;
import com.booking.cab.domain.gateway.BookingGatewayInterface;
import com.booking.cab.repository.entity.Booking;
import com.booking.cab.repository.entity.BookingRequest;
import com.booking.cab.repository.entity.Driver;

@Service
public class CreateBooking {
	
	GetClosestDriver getClosestDriver;
	
	CalculateTravelTime calculateTravelTime;
	
	BookingGatewayInterface bookingGateway;
	
	GetNow getNow;
	
	@Autowired
	public CreateBooking(
			GetClosestDriver getClosestDriver, 
			CalculateTravelTime calculateTravelTime,
			BookingGatewayInterface bookingGateway,
			GetNow getNow) {
		super();
		this.getClosestDriver = getClosestDriver;
		this.calculateTravelTime = calculateTravelTime;
		this.bookingGateway = bookingGateway;
		this.getNow = getNow;
	}

	public Booking execute(BookingRequest request) {
		Location pickUpLocation = new Location(request.getPickUpLatitude(), request.getPickUpLongitude());
		Driver driver = this.getClosestDriver.execute(pickUpLocation);
		Location dropOffLocation = new Location(request.getDropOffLatitude(), request.getDropOffLongitude());
		double timeToDestination = this.calculateTravelTime.execute(
				driver.getLocation(),
				pickUpLocation,
				dropOffLocation);
		LocalDateTime eta = this.getNow.execute().plusSeconds((long)timeToDestination);
		Booking booking = new Booking(
				0,
				request.getUserId(),
				driver,
				pickUpLocation,
				dropOffLocation,
				eta);
		Booking savedBooking = this.bookingGateway.save(booking);
		
		return savedBooking;
	}
	
}
