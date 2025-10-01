package auth.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import auth.exception.UserNotFoundException;
import auth.gateway.UserGatewayInterface;
import auth.repository.entity.User;
import auth.security.datastructure.BookingUserDetails;

@Service
public class BookingUserDetailsService implements UserDetailsService {
    private final UserGatewayInterface userGateway;
    private final PasswordEncoder encoder;
    
    public BookingUserDetailsService(UserGatewayInterface userGateway, PasswordEncoder encoder) {
        this.userGateway = userGateway;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userGateway.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + username);
        }
        return new BookingUserDetails(user);
    }

    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userGateway.save(user);
        return savedUser;
    }
}
