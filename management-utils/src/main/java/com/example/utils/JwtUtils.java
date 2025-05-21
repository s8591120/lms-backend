package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final Key key = Keys.hmacShaKeyFor("czg1OTExMjAczg1OTExMjAczg1OTExMjAczg1OTExMjA".getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRE_TIME = 12*60*60*1000;


    /**
     * 生成JWT令牌
     */
    public static String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME ))
                .compact();
    }

    /**
     * 解析令牌
     */
    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
