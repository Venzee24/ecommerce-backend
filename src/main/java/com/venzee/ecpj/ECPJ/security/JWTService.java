package com.venzee.ecpj.ECPJ.security;

import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.repository.TokenRepository;
import com.venzee.ecpj.ECPJ.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    public String refreshedToken(String token){
        String email = getEmailFromJWT(token);
        String newToken= Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SECRET) // Consider stronger algorithm (HS512 or higher)
                .compact();
        return newToken;
    }
    public String generateToken(UserDetails userDetails){
        String email = userDetails.getUsername();
        System.out.println(email);
        Date currentDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis()+ SecurityConstants.JWT_EXPIRATION*24*60*60*1000);

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SECRET)
                .compact();


        return token;
    }
    public String getEmailFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Token is expire or incorrect");
        }
    }
    public String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }

    public User getUserFromToken(String token) {
        String email = getEmailFromJWT(token);
        User user = userRepository.findByEmail(email).get();
        return user;
    }
}
