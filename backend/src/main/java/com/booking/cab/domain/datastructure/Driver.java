package com.booking.cab.domain.datastructure;

import java.io.Serializable;

import com.booking.cab.domain.businessobject.Location;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Driver implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@NotEmpty
	private String name;

    @NotNull @Valid
    @Embedded
	private Location location;
    
    public Driver() {
    	this(0, "", new Location(0.0, 0.0));
    }

	public Driver(int id, String name, Location location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
	
}
