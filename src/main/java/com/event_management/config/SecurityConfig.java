package com.event_management.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless APIs
                .authorizeHttpRequests(auth -> auth
                        // 1. Whitelist public endpoints (Login, Register, and Swagger UI)
                        .requestMatchers("/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // 2. Only Registrars and Admins can create events
                        .requestMatchers(HttpMethod.POST, "/events").hasAnyRole("REGISTRAR", "ADMIN")

                        // 3. Anyone can view events (GET requests)
                        .requestMatchers(HttpMethod.GET, "/events/**").permitAll()

                        // 4. All other requests (like registering for an event) require the user to be logged in
                        .anyRequest().authenticated()
                )
                // 5. Tell Spring we are using JWTs, so don't create sessions
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)

                // 6. Add our custom JWT filter BEFORE the standard password filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}