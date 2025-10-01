package com.booking.cab.domain.gateway;

import java.util.List;

import com.booking.cab.repository.entity.Driver;

public interface DriverGatewayInterface {
	List<Driver> findAll();
}
