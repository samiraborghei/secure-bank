package org.example.firstspringbootproject.controller;

import org.example.firstspringbootproject.dto.LoginRequest;
import org.example.firstspringbootproject.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTests {
    private LoginRequest request() {
        LoginRequest request = new LoginRequest();
        request.setUsername("samiras");
        request.setPassword("test-password");
        return request;
    }

    @Test
    void rejectedCredentialsNeverIssueToken() {
        JwtService jwt = new JwtService() {
            @Override public String generateToken(String username) {
                fail("Must not issue a token for rejected credentials");
                return null;
            }
        };
        AuthController controller = new AuthController(jwt, authentication -> {
            throw new BadCredentialsException("Invalid credentials");
        }, null);
        ResponseStatusException failure = assertThrows(ResponseStatusException.class,
                () -> controller.login(request()));
        assertEquals(401, failure.getStatusCode().value());
    }

    @Test
    void successfulLoginUsesAuthenticatedUsername() {
        JwtService jwt = new JwtService() {
            @Override public String generateToken(String username) {
                assertEquals("canonical-user", username);
                return "issued-token";
            }
        };
        AuthController controller = new AuthController(jwt, authentication -> {
            assertEquals("samiras", authentication.getName());
            assertEquals("test-password", authentication.getCredentials());
            return new UsernamePasswordAuthenticationToken("canonical-user", null, List.of());
        }, null);
        assertEquals("issued-token", controller.login(request()));
    }
}
