package adawardrobe.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private Key secretKey;

    public JwtUtil(String secretKeyString) {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    // ⏳ Durée de validité du token : 1 heure
    private final long expirationMs = 3600000;

    // 🔹 Générer un token JWT
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)               // identifiant (souvent l'email ou le pseudo)
                .claim("role", role)                // rôle (ROLE_USER / ROLE_ADMIN)
                .setIssuedAt(new Date())            // date de création
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // expiration
                .signWith(secretKey, SignatureAlgorithm.HS256) // signature
                .compact();
    }

    // 🔹 Valider le token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 🔹 Extraire le nom d'utilisateur depuis le token
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 🔹 Extraire le rôle depuis le token
    public String getRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}

