package org.example.firstspringbootproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.firstspringbootproject.dto.LoginRequest;
import org.example.firstspringbootproject.entities.UserAccount;
import org.example.firstspringbootproject.service.JwtService;
import org.example.firstspringbootproject.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserAccountService userAccountService;

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        try {
            Authentication authenticated = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));
            return jwtService.generateToken(authenticated.getName());
        } catch (AuthenticationException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    // GET CURRENT LOGGED-IN USER
    @GetMapping("/me")
    public Map<String, String> getCurrentUser(
            Authentication authentication
    ) {

        String username = authentication.getName();

        UserAccount user =
                userAccountService
                        .getUserAccountByUsername(username);

        return Map.of(
                "username", username,
                "role", user.getRole().name()
        );
    }

    // CREATE NEW USER
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(
            @RequestBody UserAccount userAccount
    ) {

        UserAccount createdUser =
                userAccountService.createUser(userAccount);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }
}
