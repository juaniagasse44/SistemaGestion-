package com.servicioGestion.supermercado.apiREST.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {

	@Value("${security.jwt.secret}")
	private String secret;

	@Value("${security.jwt.expiration-ms:86400000}")
	private long expirationMs;

	private SecretKey secretKey;

	@PostConstruct
	void init() {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(Integer userId) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + expirationMs);
		return Jwts.builder()
				.setSubject(String.valueOf(userId))
				.setIssuedAt(now)
				.setExpiration(expiry)
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();
	}

	public Optional<Integer> validateAndGetUserId(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(secretKey)
					.build()
					.parseClaimsJws(token)
					.getBody();
			String subject = claims.getSubject();
			return Optional.of(Integer.parseInt(subject));
		} catch (Exception e) {
			return Optional.empty();
		}
	}
} 