package com.booking.cab.domain.gateway;

import java.util.List;

import com.booking.cab.domain.datastructure.Driver;

public interface DriverGatewayInterface {

	List<Driver> findAll();
}
