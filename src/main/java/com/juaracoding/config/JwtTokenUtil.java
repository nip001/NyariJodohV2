package com.juaracoding.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5495373966523332175L;

	private static final long JWT_TOKEN_VALIDITY = 1*30*60;

	@Value("{jwt.secret}")
	private String secret;

	/* JIKA KITA SUDAH PUNYA TOKEN */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	public String getPayloadFromToken(String token,String target) {
		final Claims claims = getAllClaimsFromToken(token);
		return (String) claims.get(target);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	/* JIKA KITA LOGIN DAN TIDAK MEMPUNYAI TOKEN */
	
	public String generateToken(UserDetails userDetails,String jk, long id) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("jeniskelamin", jk);
		claims.put("iduser", String.valueOf(id));
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String,Object> claims, String subject) {
		Date dateExp = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);
		String token = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(dateExp)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		return token;
	}
	
	
	/* VALIDASI TOKEN */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}
