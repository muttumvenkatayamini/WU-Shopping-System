package com.wu.authservice.util;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	public static final String SECRET = "c904549caf89b0b67a4821465e853c6e59ddf27cb8fab1cd1e9b2cc714a2f8d5a0b64f085ec78f0b13af63e99ece7bedf52b7e54a413b358169484d538129009";

	public void validateToken(final String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}

	public String generateToken(String userName, String roles) {
		Map<String, String> claims = new HashMap<>();
		claims.put("roles", roles);
		return createToken(claims, userName);

	}

	private String createToken(Map<String, String> claims, String userName) {

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
