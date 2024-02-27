package com.authservice.authservice.config;

import com.authservice.authservice.entity.User;
import com.authservice.authservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepo;
    @Value("${application.security.jwt.key}")
    private  String secretKey;

    @Value("${application.security.jwt.expiration}")
    private  Long expiration;

    public String generateToken(User user){
        HashMap<String,Object> claims=new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(signinKey(secretKey))
                .compact();
    }

    public Key signinKey(String secretKey){
        byte[]keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(User user,String jwt){
        String username=extractUsername(jwt);
        return username.equals(user.getUsername());
    }

    public Claims extractClaims(String jwt){
        return Jwts.parser()
                .setSigningKey(signinKey(secretKey))
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public <T> T extractClaim(String jwt, Function<Claims,T> claimResolver){
        Claims claims=extractClaims(jwt);
        return claimResolver.apply(claims);
    }

    public String extractUsername(String jwt){
        return extractClaim(jwt,Claims::getSubject);
    }

   /* public User validateToken(String jwt){
        String username=extractUsername(jwt);
        Optional<User> user = userRepo.findByUsername(username);
        if(username.equals(user.get().getUsername()))return user.get();
        else throw new RuntimeException("user not found");
    }*/
}