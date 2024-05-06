package com.booking.cab.domain.usecase.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.booking.cab.domain.businessobject.Location;
import com.booking.cab.domain.usecase.CalculateTravelTime;

class CalculateTravelTimeTest {

	private final CalculateTravelTime calculateTravelTime;

	public CalculateTravelTimeTest() {
		super();
		this.calculateTravelTime = new CalculateTravelTime();
	}
	
	@Test
	void noLocations() {
		assertEquals(0, calculateTravelTime.execute(), 0.000001);
	}
	
	@Test
	void oneLocation() {
		Location first = new Location(0, 0);
		assertEquals(0, calculateTravelTime.execute(first), 0.000001);
	}
	
	@Test
	void twoLocations() {
		Location first = new Location(0, 0);
		Location second = new Location(1, 0);
		assertEquals(6904.6767, calculateTravelTime.execute(first, second), 0.000001);
	}
	
	@Test
	void manyLocations() {
		Location first = new Location(0, 0);
		Location second = new Location(1, 0);
		Location third = new Location(1, 1);
		assertEquals(13808.301758, calculateTravelTime.execute(first, second, third), 0.000001);
	}

}
