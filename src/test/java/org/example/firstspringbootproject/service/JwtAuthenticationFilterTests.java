package org.example.firstspringbootproject.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class JwtAuthenticationFilterTests {
    private static final String SECRET = "test-signing-key-with-at-least-32-bytes-long";

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    private JwtAuthenticationFilter filter() {
        JwtService service = new JwtService();
        ReflectionTestUtils.setField(service, "secretKey", SECRET);
        return new JwtAuthenticationFilter(service,
                username -> User.withUsername(username).password("unused").roles("USER").build());
    }

    private String token(long expirationOffset) {
        return Jwts.builder().subject("test-user")
                .expiration(new Date(System.currentTimeMillis() + expirationOffset))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    @Test
    void expiredAndMalformedTokensReturn401WithoutContinuing() throws Exception {
        for (String token : new String[]{token(-60_000), "malformed-token", ""}) {
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.addHeader("Authorization", "Bearer " + token);
            MockHttpServletResponse response = new MockHttpServletResponse();
            AtomicBoolean continued = new AtomicBoolean();
            filter().doFilter(request, response, (req, res) -> continued.set(true));
            assertEquals(401, response.getStatus());
            assertEquals("Bearer error=\"invalid_token\"", response.getHeader("WWW-Authenticate"));
            assertTrue(response.getContentAsString().contains("Please log in again"));
            assertFalse(continued.get());
            assertNull(SecurityContextHolder.getContext().getAuthentication());
        }
    }

    @Test
    void tokenForMissingUserReturns401WithoutContinuing() throws Exception {
        JwtService service = new JwtService();
        ReflectionTestUtils.setField(service, "secretKey", SECRET);
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(service, username -> {
            throw new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found");
        });
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer " + token(60_000));
        MockHttpServletResponse response = new MockHttpServletResponse();
        AtomicBoolean continued = new AtomicBoolean();
        filter.doFilter(request, response, (req, res) -> continued.set(true));
        assertEquals(401, response.getStatus());
        assertFalse(continued.get());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void validTokenAuthenticatesAndContinues() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer " + token(60_000));
        AtomicBoolean continued = new AtomicBoolean();
        filter().doFilter(request, new MockHttpServletResponse(), (req, res) -> continued.set(true));
        assertTrue(continued.get());
        assertEquals("test-user", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Test
    void missingTokenContinuesWithoutAuthentication() throws Exception {
        AtomicBoolean continued = new AtomicBoolean();
        filter().doFilter(new MockHttpServletRequest(), new MockHttpServletResponse(),
                (req, res) -> continued.set(true));
        assertTrue(continued.get());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
