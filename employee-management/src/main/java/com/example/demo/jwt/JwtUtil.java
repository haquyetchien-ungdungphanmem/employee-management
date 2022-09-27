package com.example.demo.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

@Service
public class JwtUtil {
    private int refreshExpirationDateInMs;

    private  String secret;

    private int jwtExpirationInMs;

    @Value("${jwt.refreshExpirationDateInMs}")
    public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
        this.refreshExpirationDateInMs = refreshExpirationDateInMs;
    }

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }
    @Value("${jwt.jwtExpirationInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    private String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String GenerateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        if (roles.contains(new SimpleGrantedAuthority("ROLE_Giám Đốc"))){
            claims.put("isGiamDoc",true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_Trưởng Phòng"))){
            claims.put("isTruongPhong",true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_Nhân Viên Nhân Sự"))){
            claims.put("isNhanVienNhanSu",true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_Nhân Viên"))){
            claims.put("isNhanVien",true);
        }
        return doGenerateToken(claims, userDetails.getUsername());
    }


    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException exception){
            throw new BadCredentialsException("INVALID_CREDENTIALS",exception);
        }catch (ExpiredJwtException e){
            throw e;
        }
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRoleFromToken(String token){
        List<SimpleGrantedAuthority> roles = null;
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Boolean isGiamDoc =claims.get("isGiamDoc", Boolean.class);
        Boolean isTruongPhong = claims.get("isTruongPhong", Boolean.class);
        Boolean isNhanVienNhanSu =claims.get("isNhanVienNhanSu", Boolean.class);
        Boolean isNhanVien = claims.get("isNhanVien", Boolean.class);

        if (isGiamDoc != null && isGiamDoc == true){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_Giám Đốc"));
        }
        if (isTruongPhong != null && isTruongPhong == true){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_Trưởng Phòng"));
        }
        if (isNhanVienNhanSu != null && isNhanVienNhanSu == true){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_Nhân Viên Nhân Sự"));
        }
        if (isNhanVien != null && isNhanVien == true){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_Nhân Viên"));
        }

        return roles;
    }
}
