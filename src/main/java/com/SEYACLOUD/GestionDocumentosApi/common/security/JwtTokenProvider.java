package com.SEYACLOUD.GestionDocumentosApi.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;


    private SecretKey getSigningKey() {
        try {
            String clean = jwtSecret.trim();
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] keyBytes = digest.digest(clean.getBytes(StandardCharsets.UTF_8));

            return Keys.hmacShaKeyFor(keyBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al inicializar la clave de firma JWT", e);
        }
    }

    // ================= TOKEN GENERATION =================
    public String generateToken(String idUsuario, String nombre, String ruc, String descripcionRol) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("nombre", nombre);
        claims.put("ruc", ruc);
        claims.put("rol", descripcionRol);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        System.out.println("GENERATING KEY BYTES: " +
                getSigningKey().getEncoded().length);

        return Jwts.builder()
                .subject(idUsuario)
                .claims(claims)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey(), Jwts.SIG.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    // ================= CLAIMS =================
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUserIdFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getNombreFromToken(String token) {
        return getClaims(token).get("nombre", String.class);
    }

    public String getRucFromToken(String token) {
        return getClaims(token).get("ruc", String.class);
    }

    public String getRolFromToken(String token) {
        return getClaims(token).get("rol", String.class);
    }

}