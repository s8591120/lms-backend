package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {

    private Key key = Keys.hmacShaKeyFor("czg1OTExMjAczg1OTExMjAczg1OTExMjAczg1OTExMjA".getBytes(StandardCharsets.UTF_8));

    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

//        Key key = Keys.hmacShaKeyFor("czg1OTExMjAczg1OTExMjAczg1OTExMjAczg1OTExMjA".getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0NjUyNzU2M30.nAuy4OfW0FNkK2DKuHGSzBM_wanKzCyrBvndRVU96J4";
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
