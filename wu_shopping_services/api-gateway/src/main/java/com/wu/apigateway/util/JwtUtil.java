package com.wu.apigateway.util;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

	public static final String SECRET = "c904549caf89b0b67a4821465e853c6e59ddf27cb8fab1cd1e9b2cc714a2f8d5a0b64f085ec78f0b13af63e99ece7bedf52b7e54a413b358169484d538129009";

	public void validateToken(final String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String getUserNameFromToken(final String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
		
	}

	public String getRolesFromToken(final String token) {
		Claims claims = getClaims(token);
		return claims.get("roles",String.class);
	}
	
	private Claims getClaims(final String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

}
