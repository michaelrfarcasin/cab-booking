package com.booking.cab.domain.gateway;

import java.util.List;

import com.booking.cab.domain.datastructure.User;

public interface UserGatewayInterface {

	User findById(int id);

	User findByName(String name);
	
	List<User> findAll();

	User save(User user);

	void deleteById(int id);
	
}
