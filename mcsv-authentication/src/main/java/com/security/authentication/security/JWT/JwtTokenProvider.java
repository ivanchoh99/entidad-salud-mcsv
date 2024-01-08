package com.security.authentication.security.JWT;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date time = new Date();
        Date expirationToken = new Date(time.getTime() + ConstJWT.JWT_EXPIRATION_TOKEN);

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationToken)
                .signWith(SignatureAlgorithm.ES512, ConstJWT.JWT_SIGNATURE)
                .compact();
        return token;
    }
}
