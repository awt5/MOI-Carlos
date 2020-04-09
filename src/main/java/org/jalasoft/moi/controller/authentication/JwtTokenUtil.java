/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.controller.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

/**
 * Helps to configure the token value and validate claims.
 *
 * @author Lucero Quiroga Perez
 * @version 1.3
 */
@Service
public class JwtTokenUtil {

    private String secret = "AWT_MOI";

    /**
     * Generate a new base64 token that contains user data.
     *
     * @param username a string with the user value
     * @return token with username, expiration time and a secret value
     */
    public String generateToken(String username) {
        long currentTime = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + 3000000))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
        return token;
    }

    /**
     * Verifies if the token input contains the correct user details and it is
     * into the expiration time.
     *
     * @param token with the user data
     * @param userDetails contains data to compare with token data.
     * @return true if the token is enable else false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extracts the username from the token input.
     *
     * @param token with the user data
     * @return username that token contains
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the date from the token input.
     *
     * @param token with the user data
     * @return Date that token contains
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Checks if the token is into the expiration time.
     *
     * @param token with the user data
     * @return true if is into the expiration time else false
     */
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Looks for a specific value into the token.
     *
     * @param token with the user data
     * @param claimsResolver contains an action with a specific claim
     * @param <T> type of the claim
     * @return the claim value
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Converts All token body data to Claims.
     *
     * @param token with the user data
     * @return All token body data as Claims
     */
    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }
}
