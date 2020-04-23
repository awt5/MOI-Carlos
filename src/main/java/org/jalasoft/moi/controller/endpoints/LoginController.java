/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.controller.endpoints;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the login whit correct credentials and generates a token.
 *
 * @author Carlos Meneses
 * @version 1.2
 */
/*@RestController
@RequestMapping("/login")*/
@Api(value = "login", description = "Login a user and generates a token")
public class LoginController {

    /**
     * Builds a token whit the correct credentials.
     *
     * @param username from the data base
     * @param password from the data base
     * @return token
     */
    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password")String password){
        String secret = "AWT05";
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .setSubject(username)
                .claim("Role","Admin")
                .compact();
        return token;
    }
}
