package com.booking.cab.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.booking.cab.domain.datastructure.Booking;
import com.booking.cab.domain.gateway.BookingGatewayInterface;

@Component("bookingGateway")
public interface BookingRepository extends JpaRepository<Booking, Integer>, BookingGatewayInterface {

}
