package com.booking.cab.domain.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.cab.domain.businessobject.Location;
import com.booking.cab.domain.gateway.DriverGatewayInterface;
import com.booking.cab.exception.DriverNotFoundException;
import com.booking.cab.repository.entity.Driver;

@Service
public class GetClosestDriver {
	
	DriverGatewayInterface driverGateway;
	
	@Autowired
	public GetClosestDriver(DriverGatewayInterface driverGateway) {
		super();
		this.driverGateway = driverGateway;
	}

	public Driver execute(Location pickUpLocation) {
		List<Driver> drivers = driverGateway.findAll();
		if (drivers.isEmpty()) {
			throw new DriverNotFoundException("No drivers found");
		}
		
		return drivers.stream().reduce(drivers.getFirst(), (Driver currentClosest, Driver other) -> {
			double closestDistance = currentClosest.getLocation().distanceTo(pickUpLocation);
			double otherDistance = other.getLocation().distanceTo(pickUpLocation);
			return closestDistance < otherDistance ? currentClosest : other;
		});
	}
}
