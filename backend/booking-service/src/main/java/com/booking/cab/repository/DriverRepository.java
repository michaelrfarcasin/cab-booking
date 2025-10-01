package com.booking.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.booking.cab.domain.gateway.DriverGatewayInterface;
import com.booking.cab.repository.entity.Driver;

@Component("driverGateway")
public interface DriverRepository extends JpaRepository<Driver, Integer>, DriverGatewayInterface {
}
