package auth.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for JWT handling.
 * Declaring this class with the configuration processor generates metadata for the `jwt.*` properties
 * which prevents IDE/build warnings about unknown properties.
 */
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * Base64-encoded HMAC secret used for symmetric JWT verification (HS256).
     */
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
