package com.example.expense_tracker.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    // Tajny klucz pobierany z application.properties lub domyślna wartość (minimum 256 bitów)
    @Value("${jwt.secret}")
    private String secretKey;

    // Czas ważności tokena w milisekundach (np. 24 godziny)
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // Helper do tworzenia bezpiecznego klucza kryptograficznego
    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 1. GENEROWANIE TOKENA JWT
    public String generateToken(UUID userId, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .subject(userId.toString())           // Identyfikator użytkownika (subject)
                .claim("email", email)                 // Dodatkowe pole (claim) z adresem e-mail
                .issuedAt(now)                         // Data wystawienia
                .expiration(expiryDate)                // Data wygaśnięcia
                .signWith(getSigningKey())                // Podpisanie kluczem prywatnym
                .compact();                            // Zbudowanie ciągu tekstowego (String JWT)
    }

    // 2. WYCIĄGANIE DANYCH (CLAIMS) Z TOKENA
    public UUID extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return UUID.fromString(claims.getSubject());
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("email", String.class);
    }

    // Pobieranie wszystkich claims z tokena
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 3. WALIDACJA TOKENA
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            // Token jest niepoprawny, zmodyfikowany lub wygasł
            return false;
        }
    }
}