package org.example.firstspringbootproject;

import org.example.firstspringbootproject.service.JwtAuthenticationFilter;
import org.example.firstspringbootproject.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SecurityConfigTests {
    @Test
    void securityFilterChainInitializes() {
        try (var context = new AnnotationConfigWebApplicationContext()) {
            context.setServletContext(new MockServletContext());
            context.addBeanFactoryPostProcessor(beanFactory ->
                    beanFactory.registerSingleton("jwtAuthenticationFilter",
                            new JwtAuthenticationFilter(new JwtService(),
                                    username -> User.withUsername(username)
                                            .password("unused").roles("CUSTOMER").build())));
            context.register(SecurityConfig.class);
            context.refresh();

            assertFalse(context.getBean(SecurityFilterChain.class).getFilters().isEmpty());
        }
    }
}
