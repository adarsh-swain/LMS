package com.lms.filter;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.lms.config.AppConstants;
import com.lms.config.UserInfoConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
	
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

//	public String generateToken(UserInfoConfig userInfoConfig) {
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(
//				claims, 
//				userInfoConfig.getUsername(),
//				userInfoConfig.getAuthorities(),
//			userInfoConfig.getId(),
//			userInfoConfig.getUname());
//	}
//
//	 public String createToken(Map<String, Object> claims, String userName, Collection<? extends GrantedAuthority> authorities,
//	            int id, String uname) {
//	        return Jwts.builder().setClaims(claims).setSubject(userName).claim("authorities", authorities)
//	                .claim("id", id).claim("uname", uname).setIssuedAt(new Date(System.currentTimeMillis()))
//	                .setExpiration(new Date(System.currentTimeMillis() + AppConstants.JWT_TOKEN_VALIDITY * 1000))
//	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//	    }
	
	public String generateToken(UserInfoConfig userInfoConfig) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userInfoConfig.getUsername(), userInfoConfig.getAuthorities());
    }

    // Create token with additional information
    public String createToken(Map<String, Object> claims, String userName, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .claim("authorities", authorities)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + AppConstants.JWT_TOKEN_VALIDITY * 1000))
            .signWith(getSignKey(), SignatureAlgorithm.HS256)
            .compact();
    }

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}


}
