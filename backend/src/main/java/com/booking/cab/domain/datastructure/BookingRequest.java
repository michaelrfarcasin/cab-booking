package com.booking.cab.domain.datastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class BookingRequest {
	
	@Id
	private int id;
	
	private int userId;
	
	@Min(-90) @Max(90)
	private double pickUpLatitude;
	
	@Min(-180) @Max(180)
	private double pickUpLongitude;

    @Min(-90) @Max(90)
    private double dropOffLatitude;
    
    @Min(-180) @Max(180)
    private double dropOffLongitude;
    
    public BookingRequest() {
    	this.id = 0;
    	this.userId = 0;
    	this.pickUpLatitude = 0.0;
    	this.pickUpLongitude = 0.0;
    	this.dropOffLatitude = 0.0;
    	this.dropOffLongitude = 0.0;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getPickUpLatitude() {
		return pickUpLatitude;
	}

	public void setPickUpLatitude(double pickUpLatitude) {
		this.pickUpLatitude = pickUpLatitude;
	}

	public double getPickUpLongitude() {
		return pickUpLongitude;
	}

	public void setPickUpLongitude(double pickUpLongitude) {
		this.pickUpLongitude = pickUpLongitude;
	}

	public double getDropOffLatitude() {
		return dropOffLatitude;
	}

	public void setDropOffLatitude(double dropOffLatitude) {
		this.dropOffLatitude = dropOffLatitude;
	}

	public double getDropOffLongitude() {
		return dropOffLongitude;
	}

	public void setDropOffLongitude(double dropOffLongitude) {
		this.dropOffLongitude = dropOffLongitude;
	}

	@Override
	public String toString() {
		return "BookingRequest [id=" + id + ", userId=" + userId + ", pickUpLatitude=" + pickUpLatitude
				+ ", pickUpLongitude=" + pickUpLongitude + ", dropOffLatitude=" + dropOffLatitude
				+ ", dropOffLongitude=" + dropOffLongitude + "]";
	}
}
