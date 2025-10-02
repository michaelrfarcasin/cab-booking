package com.booking.cab.security.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties(JwtProperties.class)
public class JwtSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
            )
            // Disable CSRF for stateless APIs
            .csrf(AbstractHttpConfigurer::disable)
            // Ensure we don't use sessions (stateless microservice)
            .sessionManagement(session -> session.
                sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Enable JWT-based authentication
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            )
            .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(JwtProperties props) {
        String base64Secret = props.getSecret();
        if (base64Secret == null || base64Secret.isBlank()) {
            // Let Spring Boot try to configure the decoder (e.g. jwk-set-uri) if no secret provided
            return null;
        }
        // sanitize: remove whitespace and CR/LF (files mounted from Windows may contain CRLF)
        String sanitized = base64Secret.trim().replaceAll("\\s+", "");
        byte[] keyBytes;
        try {
            keyBytes = Base64.getDecoder().decode(sanitized);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Failed to decode jwt.secret as base64 — check the secret contents (no newlines or extra characters).", ex);
        }
        SecretKey secretKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // You can customize this if you want to map claims to authorities.
        return converter;
    }
}
