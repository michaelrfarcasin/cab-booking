package com.booking.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.booking.cab.domain.gateway.UserGatewayInterface;
import com.booking.cab.repository.entity.User;

@Component("userGateway")
public interface UserRepository extends JpaRepository<User, Integer>, UserGatewayInterface {

}
