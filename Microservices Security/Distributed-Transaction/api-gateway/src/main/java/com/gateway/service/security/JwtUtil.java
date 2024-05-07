package com.gateway.service.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {
	
	@Value("abcdefghijklmnopqrstuvwxyz")
	private String SECRET_KEY;
	
	

    public String extractUsername(String token) {
    	System.out.println(isTokenExpired(token));
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Object getUserId(String token,String key) {
		return extractAllClaims(token).get(key);
	}
    
    public Object getRole(String token) {
    	return extractAllClaims(token).get("role");
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
        	final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
		} catch (ExpiredJwtException e) {
			throw new RuntimeException("TOKEN EXPIRED");
		}
    }
    
    @SuppressWarnings("deprecation")
	private Claims extractAllClaims(String token) {
    	try {
    		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    	}catch (ExpiredJwtException e) {
			throw new RuntimeException("TOKEN EXPIRED");
		}
    	
    }

	private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username,String role) {
        Map<String, Object> claims = new HashMap<>();
       
            if(Objects.nonNull(username))
            	claims.put("username", username);
            claims.put("role",role);
        
        
        
        return createToken(claims, username);
    }
    


    @SuppressWarnings("deprecation")
	private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
}
