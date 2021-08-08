package com.example.demo.JWT;

import com.example.demo.Entity.User;

import java.io.Serializable;
import java.util.Date;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtCreateToken implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(JwtCreateToken.class);

    private static final long serialVersionUID = -2550185165626007488L;

    private final long jwtExpiration = 604800000L;

    @Value(value = "dangducminh")
    private  String jwtSecret;

    public String generateToken(User user){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);
        String token = Jwts
                .builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now).setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
    //validate token
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
            return true;
        }catch(SignatureException e){
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }catch(MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }catch(ExpiredJwtException e) {
            logger.error("JWT token is expired: {}",e.getMessage());
        }catch(UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}",e.getMessage());
        }catch(IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}",e.getMessage());
        }
        return false;
    }
}
