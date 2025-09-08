package com.booking.cab.domain.gateway;

import java.util.List;

import com.booking.cab.repository.entity.User;

public interface UserGatewayInterface {

	User findById(int id);

	User findByUsername(String username);
	
	List<User> findAll();

	User save(User user);

	void deleteById(int id);
	
}
