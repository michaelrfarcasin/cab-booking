package com.booking.cab.domain.usecase;

import org.springframework.stereotype.Service;

import com.booking.cab.domain.businessobject.Location;

@Service
public class CalculateTravelTime {
	public double execute(Location... locations) {
		if (locations.length == 0) {
			return 0.0;
		}
		double travelTime = 0.0;
		Location lastLocation = locations[0];
		for (Location location : locations) {
			double distance = lastLocation.distanceTo(location);
			travelTime += distance * 100; // a trivial calculation for demo purposes only
			lastLocation = location;
		}
		
		return travelTime;
	}
}
