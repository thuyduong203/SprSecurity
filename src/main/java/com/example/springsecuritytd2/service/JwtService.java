package com.example.springsecuritytd2.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "597133743677397A244226452948404D635166546A576E5A7234753778214125";

    //tao khoa bi mat
    private Key getSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

    //giai ma JWT bang cach su dung khoa bi mat
    //=> lay thong tin trong phan payload cua JWT (phuoneg thuc getBody)
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //dsu dung extractAllClaims de extracClaim
    //tra ve thong tin cu the duoc trich xuat tu phan payload
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //extractUserName
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //generate Token:
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        //.compact() => tra ve chuoi JWT da duoc ma hoa va ky
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    //kiem tra thoi diem het han:
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //kiem tra token het han hay khong
    private boolean checkTokenExpirator(String token) {
        return extractExpiration(token).after(new Date());
    }

    //kiem tra token hop le:
    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !checkTokenExpirator(token);
    }
}
