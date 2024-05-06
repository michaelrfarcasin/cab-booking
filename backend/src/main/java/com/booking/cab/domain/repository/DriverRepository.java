package com.booking.cab.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.booking.cab.domain.datastructure.Driver;
import com.booking.cab.domain.gateway.DriverGatewayInterface;

@Component("driverGateway")
public interface DriverRepository extends JpaRepository<Driver, Integer>, DriverGatewayInterface {

}
