package com.booking.cab.domain.usecase.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booking.cab.domain.businessobject.Location;
import com.booking.cab.domain.gateway.BookingGatewayInterface;
import com.booking.cab.domain.usecase.CalculateTravelTime;
import com.booking.cab.domain.usecase.CreateBooking;
import com.booking.cab.domain.usecase.GetClosestDriver;
import com.booking.cab.domain.usecase.GetNow;
import com.booking.cab.exception.DriverNotFoundException;
import com.booking.cab.repository.entity.Booking;
import com.booking.cab.repository.entity.BookingRequest;
import com.booking.cab.repository.entity.Driver;

@ExtendWith(MockitoExtension.class)
class CreateBookingTest {
	
	private static final int USER_ID = 1;
	private static final LocalDateTime NOW = LocalDateTime.of(2000, 1, 1, 0, 0, 0);

	@InjectMocks
	CreateBooking createBooking;

	@Mock
	GetClosestDriver getClosestDriver;
	
	@Mock
	CalculateTravelTime calculateTravelTime;
	
	@Mock
	BookingGatewayInterface bookingGateway;
	
	@Mock
	GetNow getNow;

	private BookingRequest mockBookingRequest() {
		BookingRequest request = new BookingRequest();
		request.setUserId(USER_ID);
		request.setPickUpLatitude(0);
		request.setPickUpLongitude(0); 
		request.setDropOffLatitude(39.7392);
		request.setDropOffLongitude(-104.9903);

		return request;
	}
	
	@Test
	void exception_rethrowsException() {
		when(getClosestDriver.execute(any())).thenThrow(DriverNotFoundException.class);
		
		assertThrows(DriverNotFoundException.class, () -> createBooking.execute(mockBookingRequest()));
	}

	@Test
	void createBooking() {
		Driver driver = new Driver(1, "TestDriver1", new Location(0, 1));
		when(getClosestDriver.execute(any())).thenReturn(driver);
		when(bookingGateway.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);
		when(calculateTravelTime.execute(any(Location[].class))).thenReturn(10.0);
		when(getNow.execute()).thenReturn(NOW);
		BookingRequest request = mockBookingRequest();
		
		Booking actual = createBooking.execute(request);
		
		Booking expected = new Booking(
				0,
				USER_ID,
				driver,
				new Location(request.getPickUpLatitude(), request.getPickUpLongitude()),
				new Location(request.getDropOffLatitude(), request.getDropOffLongitude()),
				NOW.plusSeconds(10));
		assertEquals(expected, actual);
	}
	

}
