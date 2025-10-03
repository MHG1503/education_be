package com.mhgjoker.education.security.common.utils;

import com.mhgjoker.education.security.common.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Slf4j
public class JwtTokenUtils {

    public static final byte[] API_SECRET_KEY = SecurityConstants.JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    public static final SecretKey secretKey = Keys.hmacShaKeyFor(API_SECRET_KEY);

    public static String createToken(UserDetails userDetails){
        long expiration_time = SecurityConstants.EXPIRATION_REMEMBER;
        final Date createDate = new Date();
        final Date expirationDate = new Date(createDate.getTime() + expiration_time * 1000);

        return Jwts
                .builder()
                .signWith(secretKey)
                .claim(SecurityConstants.ROLE_CLAIMS,String.join(",",
                        userDetails
                                .getAuthorities()
                                .stream().map(GrantedAuthority::getAuthority).toList()))
                .setIssuer("education")
                .setSubject(userDetails.getUsername())
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .compact();
    }

    public static boolean isTokenValid(String token){
        return !isTokenExpiration(token);
    }

    private static boolean isTokenExpiration(String token){
        Date expirationDate = extractExpirationDate(token);
        return expirationDate.before(new Date());
    }

    private static Date extractExpirationDate(String token){
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    public static String extractUsername(String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private static Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static List<GrantedAuthority> getAuthorities(Claims claims){
        String role = (String) claims.get(SecurityConstants.ROLE_CLAIMS);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token, UserDetails userDetails){
        Claims claims = extractAllClaims(token);
        List<GrantedAuthority> authorities = getAuthorities(claims);
        String username = claims.getSubject();
        log.info(authorities.toString());
        return new UsernamePasswordAuthenticationToken(userDetails,null,authorities
        );
    }
}
