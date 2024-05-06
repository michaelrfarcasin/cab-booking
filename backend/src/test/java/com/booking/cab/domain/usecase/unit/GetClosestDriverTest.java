package com.booking.cab.domain.usecase.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booking.cab.domain.businessobject.Location;
import com.booking.cab.domain.datastructure.Driver;
import com.booking.cab.domain.gateway.DriverGatewayInterface;
import com.booking.cab.domain.usecase.GetClosestDriver;
import com.booking.cab.exception.DriverNotFoundException;

@ExtendWith(MockitoExtension.class)
class GetClosestDriverTest {

	@InjectMocks
	GetClosestDriver getClosestDriver;

	@Mock
	DriverGatewayInterface driverGateway;
	
	@Test
	void noDrivers_throwsNotFoundException() {
		when(driverGateway.findAll()).thenReturn(new ArrayList<Driver>());
		
		assertThrows(DriverNotFoundException.class, () -> getClosestDriver.execute(new Location(0, 0)));
	}
	
	@Test
	void multipleDrivers_returnsClosest() {
		List<Driver> drivers = new ArrayList<Driver>();
		Driver closest = new Driver(1, "TestDriver1", new Location(0, 1));
		drivers.add(closest);
		drivers.add(new Driver(2, "TestDriver2", new Location(1, 1)));
		when(driverGateway.findAll()).thenReturn(drivers);
		
		Driver actual = getClosestDriver.execute(new Location(0, 0));
		
		assertEquals(closest, actual);
	}
	
	@Test
	void multipleDrivers_differentOrder_returnsClosest() {
		List<Driver> drivers = new ArrayList<Driver>();
		Driver closest = new Driver(1, "TestDriver1", new Location(0, 1));
		drivers.add(new Driver(2, "TestDriver2", new Location(1, 1)));
		drivers.add(closest);
		when(driverGateway.findAll()).thenReturn(drivers);
		
		Driver actual = getClosestDriver.execute(new Location(0, 0));
		
		assertEquals(closest, actual);
	}

}
