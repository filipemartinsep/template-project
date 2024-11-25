package com.exemplo.api_user.users.controllers;

import com.exemplo.api_user.users.dtos.LoginDTO;
import com.exemplo.api_user.users.security.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticatorManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticatorManager, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.authenticatorManager = authenticatorManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {

        Boolean isUsernameValid = loginDTO.getUsername() != null && !loginDTO.getUsername().isEmpty();
        Boolean isPasswordValid = loginDTO.getPassword() != null && !loginDTO.getPassword().isEmpty();

        if (isUsernameValid && isPasswordValid) {
            try {
                Authentication authentication = authenticatorManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
                );

                UserDetails user = (UserDetails) authentication.getPrincipal();
                String jwt = jwtTokenProvider.generateToken(user);

                Cookie cookie = new Cookie("token", jwt);
                cookie.setHttpOnly(true);
                cookie.setSecure(false);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);

                return jwt;

            } catch (AuthenticationException error) {
                throw new RuntimeException("Invalid Credentials");
            }
        } else {
            throw new RuntimeException("Invalid username and password");
        }
    }
}
