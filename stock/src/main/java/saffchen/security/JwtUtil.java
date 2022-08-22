package saffchen.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.subject}")
    private String subject;
    @Value("${jwt.token.expired}")
    private String expired;
    @Value("${jwt.token.issuer}")
    private String issuer;


    public String createToken(String username, String role) {
        Date expirationDate = Date.from(ZonedDateTime.now()
                .plusMinutes(Long.valueOf(expired))
                .toInstant());
        return JWT.create()
                .withSubject(subject)
                .withClaim("username", username)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withIssuer(issuer)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC512(secret));
    }

    public String validateTokenAndGetClaim(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(secret))
                .withSubject(subject)
                .withIssuer(issuer)
                .build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}
