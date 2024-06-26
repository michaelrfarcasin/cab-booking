package com.booking.cab.domain.gateway;

import java.util.List;

import com.booking.cab.domain.datastructure.Booking;

public interface BookingGatewayInterface {

	List<Booking> findByUserId(int id);
	
	Booking save(Booking booking);

}
