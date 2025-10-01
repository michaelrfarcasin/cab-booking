package com.booking.cab.repository.entity;

import java.time.LocalDateTime;

import com.booking.cab.domain.businessobject.Location;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
public class Booking {
	
	@Id
	private int id;
	
	@NotNull
	private int userId;

	@NotNull @Valid
	@ManyToOne
	private Driver driver;

	@NotNull @Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="latitude", column=@Column(name="pick_up_latitude")),
		@AttributeOverride(name="longitude", column=@Column(name="pick_up_longitude")),
	})
	private Location pickUp;

	@NotNull @Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="latitude", column=@Column(name="drop_off_latitude")),
		@AttributeOverride(name="longitude", column=@Column(name="drop_off_longitude")),
	})
	private Location dropOff;
	
	private LocalDateTime eta;
	
	public Booking() {
		this(0, 0, new Driver(), new Location(), new Location(), LocalDateTime.now());
	}

	public Booking(
			int id, 
			@NotNull int userId, 
			@NotNull @Valid Driver driver, 
			@NotNull @Valid Location pickUp,
			@NotNull @Valid Location dropOff, 
			LocalDateTime eta) {
		super();
		this.id = id;
		this.userId = userId;
		this.driver = driver;
		this.pickUp = pickUp;
		this.dropOff = dropOff;
		this.eta = eta;
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Location getPickUp() {
		return pickUp;
	}

	public void setPickUp(Location pickUp) {
		this.pickUp = pickUp;
	}

	public Location getDropOff() {
		return dropOff;
	}

	public void setDropOff(Location dropOff) {
		this.dropOff = dropOff;
	}

	public LocalDateTime getEta() {
		return eta;
	}

	public void setEta(LocalDateTime eta) {
		this.eta = eta;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", userId=" + userId + ", driver=" + driver + ", pickUp=" + pickUp + ", dropOff="
				+ dropOff + ", eta=" + eta + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
}
