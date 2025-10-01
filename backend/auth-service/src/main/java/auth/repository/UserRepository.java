package auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import auth.gateway.UserGatewayInterface;
import auth.repository.entity.User;

@Component("userGateway")
public interface UserRepository extends JpaRepository<User, Integer>, UserGatewayInterface {
}
