package com.booking.cab.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.booking.cab.domain.datastructure.User;
import com.booking.cab.domain.gateway.UserGatewayInterface;

@Component("userGateway")
public interface UserRepository extends JpaRepository<User, Integer>, UserGatewayInterface {

}
