package webapp.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;
import java.time.Instant;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private final long expirationMs;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration-ms:3600000}") long expirationMs) {
        if (secret == null || secret.isBlank() || secret.equals("replace-me-with-secret")) {
            throw new IllegalStateException("JWT secret not set in env");
        }
        // create key from raw bytes (utf-8). In production use a strong random base64 string.
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

  public String generateToken(String username) {
    Instant now = Instant.now();
    return Jwts.builder()
            .setSubject(username)   // ✅ only email (username)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plusMillis(expirationMs)))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
}


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // @SuppressWarnings("unchecked")
    public Set<String> getRoles(String token) {
        Object val = getClaims(token).get("roles");
        if (val instanceof Collection) {
            return ((Collection<?>)val).stream().map(Object::toString).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}

