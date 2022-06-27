package com.codingdood.webapisecurity.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public class JWTUtils {
    private static String SECRET_KEY = "MySecretKey";
    private static String ISSUER = "krishna";

    /**
     * Create JWT token
     * @param payload
     * @return token
     */
    public static String createJWTToken(Map<String, ?> payload) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create().withIssuer(ISSUER).withPayload(payload)
                .sign(algorithm);
        return token;
    }

    /**
     * Verify JWT token
     * @param token
     * @return {@DecodedJWT}
     * @throws Exception
     */
    public static DecodedJWT verifyToken(final String token) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT;
        } catch (
            JWTVerificationException exception){
            //Invalid signature/claims
            throw new Exception("Invalid access", exception);
        }
    }
}
