package com.booking.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.booking.cab.domain.gateway.BookingGatewayInterface;
import com.booking.cab.repository.entity.Booking;

@Component("bookingGateway")
public interface BookingRepository extends JpaRepository<Booking, Integer>, BookingGatewayInterface {
}
